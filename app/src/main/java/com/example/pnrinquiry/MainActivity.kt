package com.example.pnrinquiry

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pnrinquiry.Adapter.FavoriteAdapter
import com.example.pnrinquiry.Adapter.PnrAdapter
import com.example.pnrinquiry.ApiClient.Client
import com.example.pnrinquiry.Model.BaseResponse
import com.example.pnrinquiry.Model.FavoriteModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tv_fav_pnr
import kotlinx.android.synthetic.main.favorite_panlist.*
import kotlinx.android.synthetic.main.item_basic_booking_info_new.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var mCall : Call<BaseResponse>? = null
    val TAG = "PnrStatus"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        psgn_list.layoutManager=LinearLayoutManager(this)
        tv_fav_pnr.layoutManager=LinearLayoutManager(this)
        tv_search.setOnClickListener {
            PnrBookingApi()
            val inputManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputManager.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    fun PnrBookingApi()
    {
        mCall = Client.getService().getPnrDetail("39d2241238msh53f7f18a81e38fcp13bd5ajsnfa0684ebc23e",tv_pnr.text.toString().trim())
        mCall!!.enqueue(responseCallback)
    }
    val responseCallback =object :Callback<BaseResponse>{
        override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            Log.d(TAG, "onFailure: "+ t.localizedMessage)
        }

        override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
            if(response.body() != null)
            {
                if(response.body()!!.passenger.size > 0)
                {
                    tv_psgn_detail.visibility= View.VISIBLE
                    ticket_fare.visibility=View.VISIBLE

                    Log.d(TAG, "onResponse: "+ "Data Received Successfully")
                    val trainname = response.body()!!.train_name
                    val trainName = arrayOf(trainname.split("-"))
                    train_name.setText(trainName[0].get(0).toString())

                    train_no.setText("(" + trainName[0].get(1).toString() +")")
                    tocitycode.setText(response.body()!!.boarding_station)
                    fromcidtycode.setText(response.body()!!.reservation_upto)

                     pnr.setText(tv_pnr.text.toString().trim())

                    journey_date.setText(response.body()!!.arrival_data.arrival_date)
                    dest_arr_date.setText(response.body()!!.departure_data.departure_date)
                    journey_time.setText(response.body()!!.arrival_data.arrival_time)
                    dest_arr_time.setText(response.body()!!.departure_data.departure_time)

                    psgn_list.adapter = PnrAdapter(applicationContext, response.body()!!.passenger)

                    val favoriteModel = ArrayList<FavoriteModel>()
                    var nouvo : Boolean = true
                    var matchIndex : Int = -1

                    tv_favpnricon.setOnClickListener {
                        val favoriteAdapter= FavoriteAdapter(applicationContext,favoriteModel)
                        favoriteModel.add(FavoriteModel(tv_pnr.text.toString().trim(), response.body()!!.passenger.get(0).name,response.body()!!.arrival_data.arrival_date))
                        /*val pnr_number = layoutInflater.inflate(R.layout.favorite_panlist,null)
                        val pnr_number_txt = pnr_number.findViewById<TextView>(R.id.tv_pnr_number)*/
                       // Log.d(TAG, "PnrData: "+pnr_number_txt.toString())
/*
                        if(favoriteAdapter.itemCount == 0)
                        {

                        }*/

                            for(i in favoriteModel.indices)
                            {
                                if(favoriteModel.get(i).pnr.equals(tv_pnr.text.toString().trim()))
                                {
                                    matchIndex = favoriteModel.indexOf(favoriteModel)

                                }
                                else
                                {
                                    //Toast.makeText(applicationContext,"Data Not Added",Toast.LENGTH_LONG).show()
                                    Log.d(TAG, "onResponse: "+ "Data Not Added")
                                    favoriteAdapter.notifyDataSetChanged()
                                }
                            }
                        tv_fav_pnr.adapter= favoriteAdapter
                    }
                }
            }
        }

    }
}