package com.example.loginassignment.ui.characters

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginassignment.BR
import com.example.loginassignment.R
import com.example.loginassignment.app.base.BaseFragment
import com.example.loginassignment.common.utils.Resource
import com.example.loginassignment.databinding.CharactersFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<CharactersViewModel, CharactersFragmentBinding>(),
    CharactersAdapter.CharacterItemListener {

    private lateinit var adapter: CharactersAdapter

    override fun bindingVariable(): Int {
        return BR.charactersViewModel
    }

    override fun viewModel(): Class<CharactersViewModel> {
        return CharactersViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.characters_fragment
    }

    override fun onViewCreated() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter(this)
        dataBinding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    dataBinding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    dataBinding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        findNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("id" to characterId)
        )
    }
}
