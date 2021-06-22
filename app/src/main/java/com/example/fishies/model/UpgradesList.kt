package com.example.fishies.model

class UpgradesList {
    companion object{
        val tackleBoxes = listOf(
            Upgrade("TackleBox", "Tiny Box", "", 100,-1,200,true),
            Upgrade("TackleBox", "Small Box", "", 500,-1,800,false),
            Upgrade("TackleBox", "Medium Box", "", 2500,-1,1500,false),
            Upgrade("TackleBox", "Big Box", "", 5000,-1,10000,false),
            Upgrade("TackleBox", "Large Box", "", 10000,-1,50000,false)
        )
        val rods = listOf(
            Upgrade("Rod","Toy Rod", "",1,-1,500,false),
            Upgrade("Rod","Plastic Rod", "",2,-1,1500,false),
            Upgrade("Rod","Bent Rod", "",3,-1,5000,false),
            Upgrade("Rod","Short Rod", "",4,-1,25000,false),
            Upgrade("Rod","Wooden Rod", "",5,-1,75000,false),
            Upgrade("Rod","Steel Rod", "",6,-1,150000,false),
            Upgrade("Rod","Long Steel Rod", "",7,-1,500000,false),
            Upgrade("Rod","Ornate Rod", "",10,-1,1500000,false),
            Upgrade("Rod","Golden Rod", "",15,-1,5000000,false),
            Upgrade("Rod","Ultimate Rod", "",20,-1,15000000,false)
        )
        val baits = listOf(
            Upgrade("Bait","Walleye Bait", "",0,0,5,false),
            Upgrade("Bait","Northern Pike Bait", "",1,0,10,false),
            Upgrade("Bait","Bluegill Bait", "",2,0,25,false),
            Upgrade("Bait","Green Sunfish Bait", "",3,0,50,false),
            Upgrade("Bait","Brook Trout Bait", "",4,0,75,false),
            Upgrade("Bait","Bool Trout Bait", "",5,0,100,false),
            Upgrade("Bait","Rainbow Trout Bait", "",6,0,150,false),
            Upgrade("Bait","Common Carp Bait", "",7,0,200,false),
            Upgrade("Bait","Fathead Minnow Bait", "",8,0,250,false),
            Upgrade("Bait","Channel Catfish Bait", "",9,0,300,false),
            Upgrade("Bait","Flathead Catfish Bait", "",10,0,350,false),
            Upgrade("Bait","Lake Chub Bait", "",11,0,400,false),
            Upgrade("Bait","Longnose Gar Bait", "",12,0,450,false),
            Upgrade("Bait","Spotted Gar Bait", "",13,0,500,false),
            Upgrade("Bait","Muskellunge Bait", "",14,0,550,false),
            Upgrade("Bait","Chain Pickerel Bait", "",15,0,600,false),
            Upgrade("Bait","Pumpkinseed Bait", "",16,0,650,false),
            Upgrade("Bait","Largemouth Bass Bait", "",17,0,700,false),
            Upgrade("Bait","Smallmouth Bass Bait", "",18,0,750,false),
            Upgrade("Bait","Small Bass Bait", "",19,0,800,false),
            Upgrade("Bait","Ruffe Bait", "",20,0,850,false),
            Upgrade("Bait","White Sucker Bait", "",21,0,900,false),
            Upgrade("Bait","Yellow Perch Bait", "",22,0,950,false),
            Upgrade("Bait","Bowfin Bait", "",23,0,1000,false),
            Upgrade("Bait","Lake Sturgeon Bait", "",24,0,2000,false)
        )
        val anglers = listOf(
            Upgrade("Angler","John", "",5,-1,5000,false),
            Upgrade("Angler","Henry", "",15,-1,25000,false),
            Upgrade("Angler","Andrew", "",30,-1,50000,false),
            Upgrade("Angler","Maria", "",60,-1,100000,false),
            Upgrade("Angler","Peter", "",90,-1,200000,false),
            Upgrade("Angler","Gill", "",120,-1,300000,false),
            Upgrade("Angler","Tom", "",180,-1,400000,false),
            Upgrade("Angler","Jack", "",300,-1,500000,false),
        )
    }
}