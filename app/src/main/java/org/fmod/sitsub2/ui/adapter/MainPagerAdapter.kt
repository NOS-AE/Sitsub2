package org.fmod.sitsub2.ui.adapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.fmod.sitsub2.ui.fragment.repo.RepoFragment
import org.fmod.sitsub2.ui.fragment.news.NewsFragment

/**
 * Create by NOSAE on 2019/10/25
 */
class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentList = ArrayList<Fragment>()

    init {
        fragmentList.run {
            add(RepoFragment.newInstance())
            add(NewsFragment.newInstance())
        }
    }

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]

}