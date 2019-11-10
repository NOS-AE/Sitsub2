package org.fmod.sitsub2.ui.fragment.repo


import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_repo.*
import kotlinx.android.synthetic.main.item_repo.*
import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseMvpFragment
import org.fmod.sitsub2.bean.Repo
import org.fmod.sitsub2.ui.adapter.RepoAdapter
import org.fmod.sitsub2.util.toastInfo
import org.fmod.sitsub2.view.FilterSheetDialogFragment


class RepoFragment : BaseMvpFragment<RepoPresenter>() {

    private lateinit var repoAdapter: RepoAdapter

    companion object {
        fun newInstance() = RepoFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_repo

    override fun initViews() {
        repo_list.layoutManager = GridLayoutManager(context,2)
        repoAdapter = RepoAdapter(
            listOf(Repo(), Repo(), Repo(),Repo(),Repo(),Repo(),Repo(),Repo(),Repo(),Repo(),Repo(),Repo()))
        repoAdapter.onSetListener = { holder ->
            holder.itemView.setOnClickListener {
                toastInfo("item ${holder.getPos()}")
            }
            holder.repo_more.setOnClickListener {
                toastInfo("more ${holder.getPos()}")
            }
        }
        repo_list.adapter = repoAdapter
    }

    override fun setListeners() {

    }
}
