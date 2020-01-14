package com.efedaniel.bloodfinder.bloodfinder.home.requesthistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.efedaniel.bloodfinder.App

import com.efedaniel.bloodfinder.R
import com.efedaniel.bloodfinder.base.BaseFragment
import com.efedaniel.bloodfinder.base.BaseViewModel
import com.efedaniel.bloodfinder.databinding.FragmentRequestHistoryBinding
import com.efedaniel.bloodfinder.extensions.onScrollChanged
import javax.inject.Inject

class RequestHistoryFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentRequestHistoryBinding
    private lateinit var viewModel: RequestHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestHistoryBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        (mainActivity.applicationContext as App).component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RequestHistoryViewModel::class.java)
        binding.viewModel = viewModel

        binding.requestHistoryRecyclerView.apply {
            adapter = RequestHistoryAdapter()
            onScrollChanged { mainActivity.invalidateToolbarElevation(it) }
        }
        viewModel.getUserRequestHistory()
    }

    private fun setUpToolbar() = mainActivity.run {
        setUpToolBar(getString(R.string.request_history), true)
        invalidateToolbarElevation(0)
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
