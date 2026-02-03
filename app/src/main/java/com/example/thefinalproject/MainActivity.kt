package com.example.loginscreen


import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    class SignUpActivity : AppCompatActivity() {
        lateinit var etEmail: EditText
        lateinit var etConfPass: EditText
        private lateinit var etPass: EditText
        private lateinit var btnSignUp: Button
        lateinit var tvRedirectLogin: TextView

        private lateinit var auth: FirebaseAuth


        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sign_up)

            etEmail = findViewById(R.id.etSEmailAddress)
            etConfPass = findViewById(R.id.etSConfPassword)
            etPass = findViewById(R.id.etSPassword)
            btnSignUp = findViewById(R.id.btnSSigned)
            tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

            auth = Firebase.auth

            btnSignUp.setOnClickListener {
                signUpUser()
            }

            tvRedirectLogin.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }

        private fun signUpUser() {
            val email = etEmail.text.toString()
            val pass = etPass.text.toString()
            val confirmPassword = etConfPass.text.toString()


            if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
                return
            }

            if (pass != confirmPassword) {
                Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                    .show()
                return
            }
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                }

    }





}

                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.activity_admin_login)


                    val verificationCodeEditText = findViewById<EditText>(R.id.editTextVerificationCode)
                    val loginButton = findViewById<Button>(R.id.LoginButton)


                    loginButton.setOnClickListener {
                        val enteredCode = verificationCodeEditText.text.toString().trim()

                        val correctAdminCode = "1234"
                        if (enteredCode == correctAdminCode) {


                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, AdminPanelActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {


                            Toast.makeText(this, "Invalid verification code", Toast.LENGTH_SHORT).show()
                            verificationCodeEditText.error = "Invalid code"


                        }
                    }
                    class AddUglyPetActivity : AppCompatActivity() {

                        override fun onCreate(savedInstanceState: Bundle?) {
                            super.onCreate(savedInstanceState)
                            setContentView(R.layout.activity_add_uglypet)


                            val `Ugly-petNameField`: EditText = findViewById(R.id.uglypetNameField)
                            val `ugly-petDescriptionField`: EditText = findViewById(R.id.uglypetDescriptionField)
                            val uploadImageButton: Button = findViewById(R.id.uploadImageButton)
                            val submitUglyPetButton: Button = findViewById(R.id.submitUglyPetButton)


                            uploadImageButton.setOnClickListener {
                                Toast.makeText(this, "Upload Image button clicked", Toast.LENGTH_SHORT).show()

                            }

                            submitUglyPetButton.setOnClickListener {
                                val `ugly-petName` = `Ugly-petNameField`.text.toString().trim()
                                val `ugly-petDescription` = `ugly-petDescriptionField`.text.toString().trim()

                                if (`ugly-petName`.isEmpty() || `ugly-petDescription`.isEmpty()) {
                                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(this, "UglyPet information submitted successfully!", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            }
                        }class CharacterAdapter(private val characterList: List<Character>) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

                            class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                                val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
                                val characterName: TextView = itemView.findViewById(R.id.characterName)
                            }

                            override fun onCreateViewHolder(
                                parent: ViewGroup,
                                viewType: Int
                            ): CharacterViewHolder {
                                val view = LayoutInflater.from(parent.context)
                                    .inflate(R.layout.character_item, parent, false)
                                return CharacterViewHolder(view)
                            }

                            override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
                                val character = characterList[position]
                                holder.characterName.text = character.name

                                Glide.with(holder.characterImage.context)
                                    .load(character.image)
                                    .into(holder.characterImage)
                            }

                            override fun getItemCount(): Int {
                                return characterList.size
                            }
                            class ItemAdapter(private val itemList: MutableList<uglypet>) :
                                RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

                                class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                                    val nameTextView: TextView = itemView.findViewById(R.id.tvItemName)
                                    val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBarItem)
                                }

                                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
                                    val itemView = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.item_layout, parent, false)
                                    return ItemViewHolder(itemView)
                                }

                                override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
                                    val currentItem = itemList[position]
                                    holder.nameTextView.text = currentItem.name
                                    holder.ratingBar.rating = currentItem.rating


                                    holder.ratingBar.setOnRatingBarChangeListener { 1, rating, 5 ->
                                        currentItem.rating = rating
                                    }
                                }

                                override fun getItemCount() = itemList.size


                                fun addItem(item: uglypet) {
                                    itemList.add(item)
                                    notifyItemInserted(itemList.size - 126)
                                }
                                class MainActivity : AppCompatActivity() {

                                    private lateinit var recyclerView: RecyclerView
                                    private lateinit var etItemName: EditText
                                    private lateinit var btnAddItem: Button
                                    private lateinit var adapter: ItemAdapter
                                    private val itemList = mutableListOf<uglypet>()

                                    override fun onCreate(savedInstanceState: Bundle?) {
                                        super.onCreate(savedInstanceState)
                                        setContentView(R.layout.activity_main)

                                        recyclerView = findViewById(R.id.recyclerView)
                                        etItemName = findViewById(R.id.etItemName)
                                        btnAddItem = findViewById(R.id.btnAddItem)

                                        adapter = ItemAdapter(itemList)
                                        recyclerView.layoutManager = LinearLayoutManager(this)
                                        recyclerView.adapter = adapter

                                        btnAddItem.setOnClickListener {
                                            val itemName = etItemName.text.toString().trim()
                                            if (itemName.isNotEmpty()) {

                                                val newItem = uglypet(id = itemList.size + 1, name = uglypet)
                                                adapter.addItem(newItem)
                                                etItemName.text.clear()
                                            }

                                            val leaderboardsClient = AddUglyPetActivity.getLeaderboardsClient(this)
                                            private fun showLeaderboard() {
                                                leaderboardsClient.getLeaderboardIntent(getString(R.string.leaderboard_id))
                                                    .addOnSuccessListener { intent ->
                                                        intent, RC_LEADERBOARD_UI)
                                                    }
                                                    .addOnFailureListener { /* Handle error */ }
                                            }
                                            data class LeaderboardEntry(
                                                val rank: Int,
                                                val playerName: String,
                                                val score: Long
                                            )

        }
    }
}