package com.example.loginsingupauth
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewFeaturesAdapter constructor(
    private val getActivity: FeaturesActivity,
    private val selectedGrade: String?,
    private val selectedSubject: String?,
    private val FeaturesList: List<Features_DataClass>
) :
    RecyclerView.Adapter<RecyclerViewFeaturesAdapter.FeaturesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_features, parent, false)
        return FeaturesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return FeaturesList.size
    }

    override fun onBindViewHolder(holder: FeaturesViewHolder, position: Int) {
        val selectedFeature = FeaturesList[position].title
        holder.tvFeaturesTitle.text = selectedFeature
        holder.ivFeaturesImg.setImageResource(FeaturesList[position].image)

        holder.cardView.setOnClickListener {
            navigateToFeatureContent(selectedFeature)
        }
    }

    private fun navigateToFeatureContent(selectedFeature: String) {

        when (selectedFeature) {
            "TEXT BOOK" -> {
                val intent = Intent(getActivity,  BookPdfMainActivity::class.java)
                intent.putExtra("selectedGrade", selectedGrade)
                intent.putExtra("selectedSubject", selectedSubject)
                intent.putExtra("selectedFeature", selectedFeature)
                getActivity.startActivity(intent)
            }
            "PAPERS" -> {
                val intent = Intent(getActivity, PastPapersActivity::class.java)
                intent.putExtra("selectedGrade", selectedGrade)
                intent.putExtra("selectedSubject", selectedSubject)
                intent.putExtra("selectedFeature", selectedFeature)
                getActivity.startActivity(intent)
            }

            "UNIT TEST" -> {
                val intent = Intent(getActivity,  BookPdfMainActivity::class.java)
                intent.putExtra("selectedGrade", selectedGrade)
                intent.putExtra("selectedSubject", selectedSubject)
                intent.putExtra("selectedFeature", selectedFeature)
                getActivity.startActivity(intent)
            }

            "QUIZ" -> {
                val intent = Intent(getActivity, BookPdfMainActivity::class.java)
                intent.putExtra("selectedGrade", selectedGrade)
                intent.putExtra("selectedSubject", selectedSubject)
                intent.putExtra("selectedFeature", selectedFeature)
                getActivity.startActivity(intent)
            }
            "TUTORIAL" -> {
                val intent = Intent(getActivity, TutorialMainActivity::class.java)
                intent.putExtra("selectedGrade", selectedGrade)
                intent.putExtra("selectedSubject", selectedSubject)
                intent.putExtra("selectedFeature", selectedFeature)
                getActivity.startActivity(intent)
            }

        }
    }

    class FeaturesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFeaturesTitle: TextView = itemView.findViewById(R.id.tvFeaturesTitle)
        val ivFeaturesImg: ImageView = itemView.findViewById(R.id.ivFeaturesImg)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}



