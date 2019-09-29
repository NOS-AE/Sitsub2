package org.fmod.sitsub2.bean

data class Suggestion(
    var text: String
) {
    companion object {
        fun toSuggestionList(array: Array<String>): ArrayList<Suggestion> {
            val res = ArrayList<Suggestion>()
            array.forEach {
                res.add(Suggestion(it))
            }
            return res
        }
    }

    override fun toString(): String {
        return text
    }
}