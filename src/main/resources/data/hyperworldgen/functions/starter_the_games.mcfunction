# Quest: Got A Wood
scoreboard players add @s quest_getting_starter 1
execute if score @s quest_getting_starter matches 1.. run function hyperworldgen:rewards/reward_quest
