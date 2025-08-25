#Task 2: Beginner Tools
#Mini-quest: collect sticks
scoreboard players add @s task_craft_stick 1
execute if score @s task_craft_stick matches 4.. run function hyperworldgen:rewards/reward_task00000002

# Main objective: craft wooden pickaxe
scoreboard players add @s task_craft_wooden_pickaxe 1
execute if score @s task_craft_wooden_pickaxe matches 1.. run function hyperworldgen:rewards/reward_task00000002_challenge

#Unlock next task (optional)
execute if score @s task_craft_wooden_pickaxe matches 1.. run function hyperworldgen:challenge/task00000003
