package org.fmod.sitsub2.ui.adapter

import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseAdapter
import org.fmod.sitsub2.bean.Repo

/**
 * Create by NOSAE on 2019/10/26
 */
class RepoAdapter(list: List<Repo>): BaseAdapter<Repo>(list) {

    override fun getLayoutId(): Int = R.layout.item_repo

    override fun onBindViewHolder(holder: ViewHolder, position: Int, value: Repo) {

    }
}