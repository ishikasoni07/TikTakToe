package com.example.tictaktoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.button1
import kotlinx.android.synthetic.main.activity_main.button2
import kotlinx.android.synthetic.main.activity_main.button3
import kotlinx.android.synthetic.main.activity_main.button4
import kotlinx.android.synthetic.main.activity_main.button5
import kotlinx.android.synthetic.main.activity_main.button6
import kotlinx.android.synthetic.main.activity_main.button7
import kotlinx.android.synthetic.main.activity_main.button8
import kotlinx.android.synthetic.main.activity_main.button9
import kotlinx.android.synthetic.main.activity_main.playertv
import kotlinx.android.synthetic.main.activity_main.resetbtn

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var Player = true
    var Turn_Count=0

    var Board_Status=Array(3){IntArray(3)}


    lateinit var board :Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i : Array<Button> in board)
        {
            for(button: Button in i)
            {
               button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()
        resetbtn.setOnClickListener {

            Turn_Count=0
            Player=true
            updateDisplay("Player 1 Turn")
            initializeBoardStatus()


        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                Board_Status[i][j]=-1
            }
        }
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                board[i][j].isEnabled= true
                board[i][j].text=""
            }
        }

    }

    override fun onClick(view: View) {
            when(view.id)
            {
                R.id.button1 -> {

                    Update_Value(row=0,col=0,player=Player)
                }
                R.id.button2 -> {

                    Update_Value(row=0,col=1,player=Player)
                }
                R.id.button3 -> {

                    Update_Value(row=0,col=2,player=Player)
                }
                R.id.button4 -> {

                    Update_Value(row=1,col=0,player=Player)
                }
                R.id.button5 -> {

                    Update_Value(row=1,col=1,player=Player)
                }
                R.id.button6 -> {

                    Update_Value(row=1,col=2,player=Player)
                }
                R.id.button7 -> {

                    Update_Value(row=2,col=0,player=Player)
                }
                R.id.button8 -> {

                    Update_Value(row=2,col=1,player=Player)
                }
                R.id.button9 -> {

                    Update_Value(row=2,col=2,player=Player)
                }

            }

        Turn_Count++
        Player=!Player
        if(Player)
        {
            updateDisplay("Player 1 Turn")
        }
        else
        {
            updateDisplay("Player 2 Turn")
        }
        if(Turn_Count==9)
        {
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {

        //Horizontal Rows

        for(i in 0..2)
        {
            if(Board_Status[i][0]==Board_Status[i][1] && Board_Status[i][0]==Board_Status[i][2])
            {
                if(Board_Status[i][0]==1)
                {
                    updateDisplay("Player 1 Wins")
                    break
                }
                else if(Board_Status[i][0]==0)
                {
                    updateDisplay("Player 2 Wins")
                    break
                }
            }
        }

        //Vertical Columns

        for(i in 0..2)
        {
            if(Board_Status[0][i]==Board_Status[1][i] && Board_Status[0][i]==Board_Status[2][i])
            {
                if(Board_Status[0][i]==1)
                {
                    updateDisplay("Player 1 Wins")
                    break
                }
                else if(Board_Status[0][i]==0)
                {
                    updateDisplay("Player 2 Wins")
                    break
                }
            }
        }

        //Left to right diagnol

        if(Board_Status[0][0]==Board_Status[1][1] && Board_Status[0][0]==Board_Status[2][2])
        {
            if(Board_Status[0][0]==1)
            {
                updateDisplay("Player 1 Wins")
            }
            else if(Board_Status[0][0]==0)
            {
                updateDisplay("Player 2 Wins")
            }
        }

        //Right to left diagnol

        if(Board_Status[0][2]==Board_Status[1][1] && Board_Status[0][2]==Board_Status[2][0])
        {
            if(Board_Status[0][2]==1)
            {
                updateDisplay("Player 1 Wins")
            }
            else if(Board_Status[0][2]==0)
            {
                updateDisplay("Player 2 Wins")
            }
        }
    }
    private fun updateDisplay(text:String)
    {
        playertv.text=text
        if(playertv.text.contains("Wins"))
        {
            buttonDisable()
        }
    }

    private fun buttonDisable()
    {
        for(i : Array<Button> in board)
        {
            for(button: Button in i)
            {
                button.isEnabled=false
            }
        }
    }

    private fun Update_Value(row: Int, col: Int, player: Boolean) {

        val text = if(Player) "X" else "0"
        val value = if(Player) 1 else 0

        board[row][col].apply {
            isEnabled=false
            setText(text)
        }

        Board_Status[row][col]=value
    }
}
