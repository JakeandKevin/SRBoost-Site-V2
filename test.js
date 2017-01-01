var amtBronze = .50;
var amtSilver = .75;
var amtGold = 1.0;
var amtPlatinum = 1.5;
var amtDiamond = 2;
var amtMaster = 7.5;
var amtGrandmaster = 75;
var prices = [amtBronze, amtSilver, amtGold, amtPlatinum, amtDiamond, amtMaster, amtGrandmaster];

function calculateCost(startingSR, desiredSR) {
    //The SR variable used to calculate the SR gains in each tier (constantly updated)
    var SR = startingSR;

    //Variables to keep track of how much SR is needed to be gained through each tier
    var SRGainBronze = 0;
    var SRGainSilver = 0;
    var SRGainGold = 0;
    var SRGainPlatinum = 0;
    var SRGainDiamond = 0;
    var SRGainMaster = 0;
    var SRGainGrandmaster = 0;

    //Again, these values need to be in an array, or else the code is far too messy later on.
    /**The amounts needed to reach the next tiers*/
    var SRGains = [SRGainBronze, SRGainSilver, SRGainGold, SRGainPlatinum, SRGainDiamond, SRGainMaster, SRGainGrandmaster];

    //Total SR needed to go from starting to desired
    var totalSRGain = desiredSR - startingSR;

    //The total cost
    var cost = 0;





    /**THE FOLLOWING IF STATEMENTS FOLLOW THE SAME LOGIC:
		 * 1. Scan tiers Bronze through Grandmaster
		 * 2. If desired rank is above current tier that is being scanned, add appropriate amount to SRGain variable
		 * 3. Otherwise, calculate remaining rank needed
		 */

    //Scan each rank tier
    var i;
    for (i = 0; i < 7; i++) {
        //(1500 for bronze, 2000 for silver, 2500 for gold, etc.)
        var tierMaxSR = 0;

        if (i == 0) tierMaxSR = 1500; //bronze
        if (i == 1) tierMaxSR = 2000; //silver
        if (i == 2) tierMaxSR = 2500; //gold
        if (i == 3) tierMaxSR = 3000; //plat
        if (i == 4) tierMaxSR = 3500; //diamond
        if (i == 5) tierMaxSR = 4000; //master
        if (i == 6) tierMaxSR = 5000; //grandmaster

        /**The following comments work on the first iteration only, for subsequent iterations, just update "Bronze" to correct rank tier name.*/

        //Check that the desired rank is above Bronze 
        if (desiredSR > tierMaxSR) {
            //Check that the starting SR isn't already above Bronze
            if (SR < tierMaxSR) {
                //Add appropriate amount to SRGainBronze
                SRGains[i] = tierMaxSR - SR;

                //Update SR variable, for we have accounted for the Bronze tier
                SR = tierMaxSR;
            }
        }

        //Runs if desiredSR is within Bronze tier
        else {
            //Add appropriate amount to SRGainBronze
            SRGains[i] = desiredSR - SR;

            //Update SR variable, for we have reached the desired rank
            SR = desiredSR;
        }
    }

    //Calculate the total price
    for (i = 0; i < 7; i++) {
        cost += (SRGains[i] * prices[i] / 100.0);
    }

    //Final Cost and Gains
    console.log("Total Skill Rating Gain: " + totalSRGain + "SR for $" + cost.toFixed(2));

    //Final Breakdown of Calculations (really for the purpose of price review)
    console.log("Breakdown:");
    for (i = 0; i < 7; i++) {
        var temp = "Skill rating gains in ";
        if (i == 0) temp += "Bronze";
        if (i == 1) temp += "Silver";
        if (i == 2) temp += "Gold";
        if (i == 3) temp += "Platinum";
        if (i == 4) temp += "Diamond";
        if (i == 5) temp += "Master";
        if (i == 6) temp += "Grandmaster";
        console.log(temp + " tier: " + SRGains[i] + "SR for $" + (SRGains[i]*prices[i]/100.0).toFixed(2));
    }
    
}