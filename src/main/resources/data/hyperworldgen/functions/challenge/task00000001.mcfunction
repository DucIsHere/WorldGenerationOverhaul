# Task 1: Getting Starter
# Mini-quest: Getting Wood

# Mini-quest: collect wood
scoreboard players add @s task_getting_wood 1
execute if score @s task_getting_wood matches 10.. run function hyperworldgen:rewards/reward_task00000001

# Main objective: craft crafting table
scoreboard players add @s task_craft_table 1
execute if score @s task_craft_table matches 1.. run function hyperworldgen:rewards/reward_task00000001_challenge

# Unlock next task (optional)
execute if score @s task_craft_table matches 1.. run function hyperworldgen:challenge/task00000002
