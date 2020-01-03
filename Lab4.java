public class Lab4
{
    public static void turnRight()
    {
        //precondition: Robot is facing whatever direction
        //postcondition: Robot turns left three times to face right
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void turnAround()
    {
        //precondition: Robot is facing whatever direction
        //postcondition: Robot turns left two times to face the opposite direction
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void backUp()
    {
        //precondition: Robot is on some tile
        //postcondition: Robot backs up to the tile behind it
        turnAround();
        Robot.move();
        turnAround();
    }

    public static void completeBars()
    {
        Robot.turnLeft();
        ifFirstTileIsDark();
        while (!Robot.onDark())
        {
            Robot.move();  
            if (Robot.onDark())
            {
                turnAround();
                goingDown();
                moveToNewColumn();
            }
        }
    }

    public static void ifFirstTileIsDark()
    {
        //precondition: Robot is facing north and is standing on the first tile of the first column
        //postcondition: Robot moves right to a new column if the tile it was on is dark. If the tile was light, then nothing happens
        //is the tile at the bottom of the column dark?
        if (Robot.onDark())
        {
            //the first tile is dark!
            turnRight();
            Robot.move();
            Robot.turnLeft();
        }   
    }

    public static void moveToNewColumn()
    {
        //precondition: Robot is facing east 
        //postcondition: Robot moves to the bottom of a new column
        //is the front of the robot clear?
        Robot.turnLeft();
        if (Robot.frontIsClear())
        {
            //the front of the robot is clear!
            Robot.move();
            firstTileIsDark();
        }
    }

    public static void goingDown()
    {
        //precondition: Robot is facing south and is on a dark tile somewhere on a column
        //postcondition: Robot makes the light tiles in front of it dark and is now at the bottom of the column
        while (Robot.frontIsClear())
        {
            Robot.move();
            Robot.makeDark();
        }
        if (!Robot.onDark())
        //to make the bottom of the column dark
        {
            Robot.makeDark();   
        }    
    }

    public static void firstTileIsDark()
    {
        //precondition: Robot is facing east and is on the bottom of a column
        //postcondition: Robot is facing north and is either still at the bottom of the same column, or moved to a new column
        //is the bottom of the column dark?
        if (Robot.onDark())
        {
            //the bottom of the column is dark!
            //is the front of the robot clear?
            if (Robot.frontIsClear())
            {
                //the front of the robot is clear! move to a new column
                Robot.move();
                Robot.turnLeft();
            }
        }
        else
        {
            //the bottom of the column is not dark!
            Robot.turnLeft();
        }   
    }

    public static void testCompleteBars1()
    {
        Robot.load("bars1.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void testCompleteBars2()
    {
        Robot.load("bars2.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void combinePiles()
    {
        swapBlackTile();
        while (Robot.frontIsClear())
        {
            Robot.move();
            swapBlackTile();
        }
    }

    public static void goToRightPile()
    {
        //precondition: Robot is on a dark tile in the left pile
        //postcondition: Robot makes the dark tile light and moves to the bottom of the right pile
        Robot.makeLight();
        turnAround();
        while (Robot.frontIsClear())
        {
            Robot.move();   
        }
        Robot.turnLeft();
        Robot.move();
        Robot.turnLeft();   
    }

    public static void returnToLeftPile()
    {
        //precondition: Robot is on the right pile and facing south
        //postcondition: Robot is on the bottom of the left pile and facing north
        while (Robot.frontIsClear())
        {
            Robot.move();
        }
        turnRight();
        Robot.move();
        turnRight();    
    }

    public static void swapBlackTile()
    {
        //precondition: Robot is facing north on a tile in the left pile
        //postcondition: If the tile the robot is on is dark, then the robot makes the tile light and adds a dark tile to the right pile
        //is the tile the robot on dark?
        if (Robot.onDark())
        {
            //the tile the robot on is dark!
            goToRightPile();
            while (Robot.onDark())
            {
                Robot.move();    
            }
            Robot.makeDark();
            turnAround();
            returnToLeftPile();
        }    
    }

    public static void testCombinePiles1()
    {
        Robot.load("piles1.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void testCombinePiles2()
    {
        Robot.load("piles2.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void connectDots()
    {
        while (Robot.onDark())
        {
            doubleMove();
            if (Robot.onDark())
            {
                darkenMiddleTile();
            }
            else
            {
                turnRightAndCheck();
                if (Robot.onDark())
                {
                    darkenMiddleTile();
                }
                else
                {
                    turnAroundAndCheck();
                    if (Robot.onDark())
                    {
                        darkenMiddleTile();   
                    }
                }
            }
        }

    }

    public static void darkenMiddleTile()
    {
        //precondition: The robot is on a dark tile
        //postcondition: The robot moves backwards and darkens the tile, then moves back to where it was before
        backUp();
        Robot.makeDark();
        Robot.move();
    }

    public static void turnAroundAndCheck()
    {
        //precondition: Robot is facing a certain direction
        //postcondition: Robot moves backwards two tiles, turns around, and moves two tiles in that direction
        doubleBackUp();
        turnAround();
        doubleMove();
    }

    public static void turnRightAndCheck()
    {
        //precondition: Robot is facing a certain direction
        //postcondition: Robot moves backwards two tiles, turns right, and moves two tiles in that direction
        doubleBackUp();
        turnRight();
        doubleMove();    
    }

    public static void doubleMove()
    {
        //precondition: Robot is facing a certain direction
        //postcondition: Robot moves up two tiles in that direction
        Robot.move();
        Robot.move();
    }

    public static void doubleBackUp()
    {
        //precondition: Robot is facing a certain direction
        //postcondition: Robot backs up a tile
        turnAround();
        doubleMove();
        turnAround();
    }

    public static void testConnectDots1()
    {
        Robot.load("connect1.txt");
        Robot.setDelay(0.025);
        connectDots();
    }

    public static void testConnectDots2()
    {
        Robot.load("connect2.txt");
        Robot.setDelay(0.025);
        connectDots();
    }
}
