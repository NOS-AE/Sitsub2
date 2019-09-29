@file:JvmName("SuggestionAdapterKt")

package org.fmod.sitsub2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_suggestion.view.*
import org.fmod.sitsub2.R
import org.fmod.sitsub2.bean.Suggestion
import kotlin.collections.ArrayList

private const val layoutId = R.layout.item_suggestion

/**
 * 自定义SuggestionAdapter来显示输入时的建议popUp（Item: text，removeButton）
 */

/**
 * 泛型为自定义类型，要使选择suggestion返回想要的字符串
 * 方法一：重载自定义类型toString
 * 方法二；自定义Filter，重载convertResultToString
 * 此处选择方法一
 */

class SuggestionAdapter(context: Context, suggestions: ArrayList<Suggestion>): ArrayAdapter<Suggestion>(context, layoutId, suggestions) {

    // ArrayAdapter内部使用mObject用于显示建议
    // 一旦ArrayFilter被使用，其内部将会用另一个对象保存源数据，mObject此时相当于被过滤后的列表
    // 因此如果自定义Filter，需自己保存originalList
    /*val originalList = ArrayList(suggestions)
    private val mFilter = object : Filter(){

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as Suggestion).text
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val res = FilterResults()
            if(constraint != null){
                if(constraint.isEmpty()) {  //输入长度为0，直接返回所有建议
                    res.values = originalList
                    res.count = originalList.size
                } else {    //过滤
                    val filter = constraint.toString().toLowerCase(Locale.ENGLISH)
                    val entries = ArrayList<Suggestion>()
                    originalList.forEach {
                        if (it.text.toLowerCase(Locale.ENGLISH).contains(filter))
                            entries.add(it)
                    }
                    res.values = entries
                    res.count = entries.size
                }
            }
            return res
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            clear()
            val res = results?.values as? ArrayList<Suggestion>
            if(!res.isNullOrEmpty()){   //显示过滤后的建议
                addAll(results.values as ArrayList<Suggestion>)
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }*/

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val res: View
        if(convertView == null) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_suggestion, parent, false)
            res = view
            res.tag = ViewHolder(view)
        } else {
            res = convertView
        }
        val holder = res.tag as ViewHolder
        holder.text.text = getItem(position)?.text
        holder.remove.setOnClickListener {
            //TODO 删除suggestion
        }
        return res
    }

    /*override fun getFilter(): Filter {
        return mFilter
    }*/

    inner class ViewHolder(itemView: View) {
        val text: TextView = itemView.suggestion_text
        val remove: ImageView = itemView.suggestion_remove
    }

}