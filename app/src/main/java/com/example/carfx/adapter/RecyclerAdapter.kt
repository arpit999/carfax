package com.example.carfx.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.carfx.DetailActivity
import com.example.carfx.R
import com.example.carfx.model.CarList
import com.squareup.picasso.Picasso
import java.text.DecimalFormat


class RecyclerAdapter(private val listings: CarList, private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val currentItem = listings.listings[position]

        holder.apply {
            Picasso.get().load(currentItem.images.firstPhoto.large)
                .placeholder(R.drawable.placeholder).into(image)
            carName.text =
                "${currentItem.year} ${currentItem.make} ${currentItem.model} ${currentItem.trim} "
//            price.text = "${currentItem.currentPrice} | ${currentItem.mileage}"
            price.text = "$"+currencyFormat(currentItem.currentPrice) +" | "+ (currentItem.mileage)/1000 +"k mi"
            location.text = "${currentItem.dealer.city}, ${currentItem.dealer.state}"

            //Dealer Call
            callDealer.setOnClickListener {

                try {
                    val callIntent = Intent(Intent.ACTION_DIAL)
                    callIntent.data = Uri.parse("tel:${currentItem.dealer.phone}")
                    context.startActivity(callIntent)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "No SIM Found", Toast.LENGTH_LONG).show()
                }
            }

            // Item click
            cardView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("LISTING", listings.listings[position])
                context.startActivities(arrayOf(intent))
            }
        }

    }

    override fun getItemCount(): Int {
        return listings.listings.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.carImageView)
        var carName: TextView = itemView.findViewById(R.id.carNameTextview)
        var location: TextView = itemView.findViewById(R.id.locationTextView)
        var price: TextView = itemView.findViewById(R.id.priceMilageTextView)
        var callDealer: TextView = itemView.findViewById(R.id.callDealerTextView)
        var cardView: CardView = itemView.findViewById(R.id.cardView)

    }

    private fun currencyFormat(amount: Int): String? {
        val formatter = DecimalFormat("###,###,##0")
        return formatter.format(amount)
    }

}