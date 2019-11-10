package org.fmod.sitsub2.ui.fragment.news


import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseMvpFragment
import org.fmod.sitsub2.ui.fragment.repo.NewsPresenter

class NewsFragment : BaseMvpFragment<NewsPresenter>() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_news

    override fun initViews() {

    }

    override fun setListeners() {

    }
}