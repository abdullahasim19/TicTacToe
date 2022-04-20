package com.myapp.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
//import kotlin.random.Random
import  java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun buClick(view: View)
    {
        val selected=view as Button
        var cellID=0
        when(selected.id)
        {
            R.id.b1->cellID=1
            R.id.b2->cellID=2
            R.id.b3->cellID=3
            R.id.b4->cellID=4
            R.id.b5->cellID=5
            R.id.b6->cellID=6
            R.id.b7->cellID=7
            R.id.b8->cellID=8
            R.id.b9->cellID=9

        }
//        Log.d("Button Selected ",cellID.toString())
        playGame(cellID,selected)
    }
    var activeplayer=1
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()
    var player1WinCount=0
    var player2WinCount=0
    var gameOver=false
    fun playGame(cellID:Int,bClick:Button)
    {
        if(activeplayer==1)
        {
            bClick.text="X"
            bClick.setBackgroundResource(R.color.design_default_color_error)
            player1.add(cellID)
            activeplayer=2
            autoPlay()
        }
        else if(activeplayer==2)
        {
            bClick.text="O"
            bClick.setBackgroundResource(R.color.purple_700)
            player2.add(cellID)
            activeplayer=1

        }
        bClick.isEnabled=false
        checkWinner()
        if(gameOver)
        {
            Toast.makeText(this,"Game Over!",Toast.LENGTH_SHORT).show()
            showDialog("Its a Draw")
            restartGame()
        }
    }
    fun checkWinner()
    {
        var winner=-1
        //row wise
        if(player1.contains(1)&&player1.contains(2)&&player1.contains(3))
            winner=1
        else if(player2.contains(1)&&player2.contains(2)&&player2.contains(3))
            winner=2

        else if(player1.contains(4)&&player1.contains(5)&&player1.contains(6))
            winner=1
        else if(player2.contains(4)&&player2.contains(5)&&player2.contains(6))
            winner=2

        else if(player1.contains(7)&&player1.contains(8)&&player1.contains(9))
            winner=1
        else if(player2.contains(7)&&player2.contains(8)&&player2.contains(9))
            winner=2
        //column wise

        else if(player1.contains(1)&&player1.contains(4)&&player1.contains(7))
            winner=1
        else if(player2.contains(1)&&player2.contains(4)&&player2.contains(7))
            winner=2

        else if(player1.contains(2)&&player1.contains(5)&&player1.contains(8))
            winner=1
        else if(player2.contains(2)&&player2.contains(5)&&player2.contains(8))
            winner=2

        else if(player1.contains(3)&&player1.contains(6)&&player1.contains(9))
            winner=1
        else if(player2.contains(3)&&player2.contains(6)&&player2.contains(9))
            winner=2

        if(winner==1)
        {
            Toast.makeText(this,"Player 1 Wins",Toast.LENGTH_SHORT).show()
            player1WinCount++
            showDialog("Player 1 Wins")
            restartGame()
        }
        else if(winner==2)
        {
            Toast.makeText(this,"Player 2 Wins",Toast.LENGTH_SHORT).show()
            player2WinCount++
            showDialog("Player 2 Wins")
            restartGame()
        }

    }
    fun autoPlay()
    {
        var emptycells=ArrayList<Int>()
        for(c:Int in 1..9)
        {
            if(!player1.contains(c) && !player2.contains(c)) {
                emptycells.add(c)
                Log.d("meow",c.toString())
            }
        }
        if(emptycells.size==0) {
            gameOver=true
            return
        }

        var r=Random()
        var indexSelect=r.nextInt(emptycells.size)
        val cellid=emptycells[indexSelect]
        var newButton:Button
        when(cellid)
        {
            1->newButton=findViewById(R.id.b1)
            2->newButton=findViewById(R.id.b2)
            3->newButton=findViewById(R.id.b3)
            4->newButton=findViewById(R.id.b4)
            5->newButton=findViewById(R.id.b5)
            6->newButton=findViewById(R.id.b6)
            7->newButton=findViewById(R.id.b7)
            8->newButton=findViewById(R.id.b8)
            9->newButton=findViewById(R.id.b9)
            else->newButton=findViewById(R.id.b1)
        }
        playGame(cellid,newButton)

    }
    fun restartGame()
    {
        gameOver=false
        activeplayer=1
        player1.clear()
        player2.clear()
        var newButton:Button
        for(cellid in 1..9)
        {
            when(cellid)
            {
                1->newButton=findViewById(R.id.b1)
                2->newButton=findViewById(R.id.b2)
                3->newButton=findViewById(R.id.b3)
                4->newButton=findViewById(R.id.b4)
                5->newButton=findViewById(R.id.b5)
                6->newButton=findViewById(R.id.b6)
                7->newButton=findViewById(R.id.b7)
                8->newButton=findViewById(R.id.b8)
                9->newButton=findViewById(R.id.b9)
                else->newButton=findViewById(R.id.b1)
            }
            newButton.text=""
            newButton.setBackgroundResource(R.color.start)
            newButton.isEnabled=true
        }
        Toast.makeText(this,"Player 1 $player1WinCount Player 2 $player2WinCount",Toast.LENGTH_SHORT).show()

    }
    fun showDialog(titles:String)
    {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(titles)
        //set message for alert dialog
        builder.setMessage("Do you want to Play Again?")

        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->

        }

        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
            finish()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }
}