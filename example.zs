import mods.mythicbotany.CTRunePosition;

//Use 3 coals and 10K mana craft a diamond.
//This is just one example and may not work in the minecraft. Because multiple identical items will be stacked.
<recipetype:mythicbotany:infusion>.addRecipe("test",<item:minecraft:diamond>,[<item:minecraft:coal>,<item:minecraft:coal>,<item:minecraft:coal>],10000,0xfffff,0x00000);

//Remove terra ingot recipe in infusion.
<recipetype:mythicbotany:infusion>.removeRecipe(<item:botania:terrasteel_ingot>);

//Remember import package!
var pos as CTRunePosition = new CTRunePosition(<item:minecraft:snowball>,1,1,false);  //Dynamic get CTRunePosition.
var pos1 as CTRunePosition = CTRunePosition.getPosition(<item:minecraft:snowball>,-1,-1,false); //Static get CTRunePosition.
var pos2 as CTRunePosition = new CTRunePosition(<item:minecraft:snowball>,1,-1,true);  //Dynamic get CTRunePosition,but snowball will consume.
var pos3 as CTRunePosition = CTRunePosition.getPosition(<item:minecraft:snowball>,-1,1,true); //Static get CTRunePosition,but snowball will consume.

//Recipe need a array!
var posArray as CTRunePosition[] = [
    pos,pos1,pos2,pos3
];

//Use 4 snowballs and a water bucket,the center item is coal to craft a snowblock.
<recipetype:mythicbotany:rune_ritual>.addRecipe("test1",<item:minecraft:coal>,posArray,100000,100,[<item:minecraft:water_bucket>],[<item:minecraft:snow_block>]);

//Remove kvasir blood recipe.
<recipetype:mythicbotany:rune_ritual>.removeRecipe("mythicbotany:mythicbotany_rune_rituals/kvasir_blood");
