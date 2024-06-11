How to use the program 

When the program is run you begin on the start panel screen. From there you click the yellow middle button that says “Play”. This brings you to the map panel where you can choose one of 
ten unique levels by clicking on the button corresponding to each level. When clicking on a level it brings you to a screen with an angry bird on a slingshot to the left of the screen, 
more angry birds in a hot bar at the bottom, and a series of blocks and pigs on the right side of the screen. The goal of the game is to destroy as many blocks and pigs as possible using 
the birds. To do this click and hold the slingshot and drag back aiming the slingshot to hit the blocks and birds. Once the aim is set let go and fire the bird. The next bird will 
automatically spawn into the slingshot once the first bird hits the ground and disappears. Some birds have special abilities. To activate the bird’s special ability, click on the screen 
with your mouse after you have fired the bird. Chuck (the yellow bird) increases in speed when activating his ability. Bomb (the black bird) explodes when activating his special ability. 
Matilda (the white bird) drops an egg that explodes when activating her special ability. After all the birds are used or after all the pigs die the game ends and you are brought to a 
post-game panel showcasing your score along with the amount of starts you received which is based on your score. The post-game panel also has two buttons one to restart the level you 
just played and another to go back to the map screen to play a different level. 

Problems encountered while creating the program 

When we first started researching how to create angry birds, we realized we needed to import an external physics library. This was a challenge at first as we had never done it before and
the method, we tried using to import the library didn’t work at first. However, after some more research and following the steps you sent us in an email on how to import the library, we 
figured it out and were successfully able to importthe library. 

We couldn’t get the initial start screen panel to show up when we were manually swapping JPanels off the JFrame. However, once we figured out how to properly implement the Card layout this 
issue was solved. 

When dragging the mouse back and pulling back the slingshot the bird’s power continued to increase even after the slingshot was at its max length. To fix this we found the change in x over the
change in y of the slingshot pullback instead of going based on where the mouse was on screen to determine the power of the slingshot. 

When finding block, pig, and birds image online they were all different sizes and did not work coherently in our code as some were too small and some too big. To fix this we used an third party
image resizer software and resized the images so they could work with our code output. 

 

Features we were not able to implement in code and why 

We were not able to implement TNT into our code as we already have the bomb bird, and his ability simulates a TNT explosion, so we didn’t think the TNT block was necessary. In addition to this
we were experiencing lag issues due to computational output and the TNT block would have greatly increased computational output causing more lag. 

We were not able to implement multiplayer into our game due to time constraints and we found that it was not necessary. The actual Angry Birds game didn’t have multiplayer and we also eventually
decided multiplayer wouldn’t work for a game like Angry Birds. Also, if we added multiplayer, it would have taken away from other necessary features such as adding the birds' abilities. 

We were not able to implement the make-your-own-map feature due to time constraints and other complications with the feature. In the make-your-own-map feature, we realized it would take too 
much time and could cause computational power issues due to the fact we aren’t creating the level, but the player is. We chose to value other aspects of the game to implement and make them 
better over trying to implement a make-your-own-map feature. 



What we learned in creating this assignment 

Arin – I learned how to use the JBox2D library and its several different features. I learned how to create different JPanels such as the start panel and map panel as well as efficiently switch
between them using card layout and the buttons. Additionally, I learned how to implement music such as the background music and the sound clips for all the birds and blocks. Finally, I learned 
to build maps and position the blocks in a certain way to adhere to the imported JBox2D physics library. 

Isaac – I learned how to import external libraries such as JBox2D and how to use the features of this library. For instance, when creating GamePanel I used the JBox2D physics for the slingshot
and blocks. I also learned how to calculate the total impulse from contact and compare it to the damage threshold to decide if damage should be taken. Additionally, I learned how to implement different features such as the bomb and superspeed abilities for different birds. Finally, I learned how to efficiently debug our code as we
faced issues with the bird abilities and implementing the physics in several game objects. 

William – I learned how to use the JBox2D library and implement its features into our code. For instance, I learned how to import different images and fonts in the code as well as work with two JPanels at the same time. In addition, I learned the 
Affinetransform class which allows me be able to draw a rotating image. Finally, I learned the mathematics and physics of the launch trajectory. 

All: We all learned how to use the JBox2D library and to implement different features of the library such as creating a body and fixture for each object or changing the coordinates from pixels
to the world to display the output properly on screen. Additionally, we all learned to better our teamwork and collaboration skills! 

 

 