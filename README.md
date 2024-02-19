
# Lost In Clouds

## Storyline

In a world where civilization clings to the sky, flying cities and islands connected by intricate networks of bridges serve as the last bastions of humanity. As the captain of an airship, your journey begins in the bustling flying city of Aeolus. Your grandfather, a renowned inventor, left behind a mysterious journal filled with cryptic sketches and clues about a long-lost artifact known as the 'Eternal Dynamo.' 

The device is rumored to have the ability to keep entire cities levitated indefinitely.Your task is to explore cities and islands in search of this priceless artifact, battling hostile creatures and navigating dangerous networks of bridges. It is up to you and your persistence to search through the mazes and find the ancient artifact.
## Screenshots

#### Island
![Island](https://via.placeholder.com/468x300?text=App+Screenshot+Here)  

#### City
![City](https://via.placeholder.com/468x300?text=App+Screenshot+Here)  

#### Equipment
![Equipment](https://via.placeholder.com/468x300?text=App+Screenshot+Here)  


## Mechanics

### Procedurally generated map

Each game generates a unique set of cities and islands, ensuring that no two adventures are be the same. The layout varies, offering a fresh experience with each attempt.


#### Fields

1. ` ` - void
2. `@` - player
3. `#` - bridge
4. `.` - island or floor
5. `|`, `-` - walls
6. `1-5` - enemies (number symbolizes difficulty level)
7. `i` - item(s)
8. `*` - potion


### Permadeath

When you die, you lose everything. Become victorious or forgotten forever.


### Combat



### Equipment

#### Slots
1. [Helm](#-helms)
2. [Chestplate](#-chestplates)
3. [Shoes](#-shoes)
4. [Weapon](#-weapons)
5. [Special Item](#-special-items)

### Statistics

#### 👟 Agility

Higher agility increases the likelihood of avoiding enemy attacks.

#### 🗡️ Attack

The amount of damage dealt to your opponents increases with your total attack.

#### 🛡️ Defense

The amount of defense is inversely proportional to the amount of damage received.

#### ❤️ Health

When the entity's health reaches zero, it is dead. In the case of the player, it means the end of the game.

## Monsters

### Thought Eater
★☆☆☆☆

**Agility:** 4\
**Attack:** 1\
**Defense:** 2\
**Health:** 3

**Behavior:** They move in a chaotic manner. Their actions are solely driven by the instinct to survive.

### Myr
★★☆☆☆

**Agility:** 7\
**Attack:** 3\
**Defense:** 3\
**Health:** 7

### Inevitable
★★★☆☆

**Agility:** 10\
**Attack:** 5\
**Defense:** 5\
**Health:** 10

**Behavior:** They can spot you from anywhere. You can run, but you can't hide.

### Modron
★★★★☆

**Agility:** 15\
**Attack:** 10\
**Defense:** 20\
**Health:** 18

### Clockwork Dragon
★★★★★

**Agility:** 2\
**Attack:** 10\
**Defense:** 30\
**Health:** 25

**Behavior:** They move slowly but are impossible to walk around.

## Items



### Helms

#### Basic Helm

**Defense:** 2  
**Health:** 2  
**Intelligence:** 3

**Intelligence multiplier:** x1.1

#### Helmet Of Enlightenment

**Defense:** -10  
**Health:** -4  
**Intelligence:** 17

**Intelligence multiplier:** x1.75


### Chestplates

#### Cloth Armor

**Defense:** 4  
**Health:** 4  

**Defense multiplier:** x1.2  
**Health multiplier:** x1.1  

#### Chainmail

**Agility:** -5  
**Defense:** 7  
**Health:** 10  


**Defense multiplier:** x1.5  
**Health multiplier:** x1.2

#### Crystaline Armor

**Defense:** 20  
**Health:** 15  

**Defense multiplier:** x2.0  
**Health multiplier:** x1.5  


### Shoes

#### Worn Out Shoes

**Agility:** 1  
**Defense:** 2   

**Agility multiplier:** x1.1  
**Defense multiplier:** x1.1  

#### Sandals

**Agility:** 6  
**Defense:** 3  
**Health:** 1  


**Agility multiplier:** x1.3  
**Defense multiplier:** x1.1  

#### Aether Shoes

**Agility:** 17  
**Defense:** 6  
**Health:** 3  


**Agility multiplier:** x2.0  
**Defense multiplier:** x1.25  


### Weapons

#### Stick

**Attack:** 2  
**Agility:** 1  

**Attack multiplier:** x1.1  
**Agility multiplier:** x1.1  

#### Common Sword

**Attack:** 3  

**Attack multiplier:** x1.25  

#### Clockwork Axe

**Attack:** 7  

**Attack multiplier:** x1.75


### Special items

#### Feather

**Agility:** 1  
**Charisma:** 2  
**Intelligence:** 3  

**Agility multiplier:** x1.1  
**Charisma multiplier:** x1.1  
**Intelligence multiplier:** x1.1  

#### Ancient Scroll

**Agility:** 2  
**Attack:** 3  
**Charisma:** 20  
**Defense:** 3  
**Health:** 10  
**Intelligence:** 12  

**Agility multiplier:** x1.5  
**Charisma multiplier:** x2.25  
**Defense multiplier:** x1.1  
**Intelligence multiplier:** x3  

#### Eternal Dynamo

**Agility:** 15  
**Attack:** 10  
**Defense:** 25  
**Health:** 30  

**Attack multiplier:** x2   


### Consumables

#### Health Potion

**Effect:** Heals 2 health.
## Building

To build this project run

```bash
    mvn package
```


## Run

To run the game use

```bash
    java -jar <name>.jar
```

