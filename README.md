



### Dev Log

----

#### First Entry - Let's get started

I've just received the specification after a short phone call. The Address Book being command line based does placate some unease at creating a workable GUI as while Java has been my main language for almost two years now, the projects I've worked on have not used any sort of GUI. I could probably still make one given enough time, but time is limited and it being console based allows me to allocate that time elsewhere.

I received the email with the specification at 14:06 BST and have just under four hours to complete the task, so the final commit should at or before 18:06 BST.

Looking at the specification, I know that I will need to use an encoding library. It's possible to go without, perhaps relying on Java's `Serializable` interface and saving the raw binary into files, however to ensure easy debugging, I will likely choose JSON to store address entries in a readable format. This is somewhat of a cheat, and I will move away from it if I can, but I don't want to get stuck on reinventing the wheel of data encoding, resulting in a non-functional application at the deadline. If there is time remaining, I will begin the process of transitioning to a new, custom made system.

The specification also includes file saves on program exit. I will have to look into catching abrupt program exits, as the plugins I am used to making are designed around a `onDisable()` method that can gracefully handle deliberate shutdowns.

#### Second Entry - Tick Tock

It is now two hours into the task and I think the barebones of the application is coming along nicely. So far it has been creating data classes, which in Java can take awhile because of the standards of encapsulation. I have yet to do any of the console interactions, instead focusing more on the back end. The next hour will be dedicated to finishing the back end and the final hour will be more focused on the user interface.

I think time could have been saved if I had ruled out a different encoding system and committed to using JSON. I have gone with an interface and implementation style system which sunk around 20-30 minutes time as I contemplated designs that would be useful enough to include but generic enough to be universal between a JSON implementation and a custom implementation.

#### Third Entry - Uh oh

It is certain that I'm not going to complete this task in time.

#### Fourth Entry - Hindsight

It's 18:07. I managed to jank some things into semi-functionality but there is much I wish I could improve on. I spent far too much time over-engineering the back end of the application. The biggest example of this is the deserialisation of JSON. In an attempt to make it as robust as possible, I spent far too much time type checking which ended up with a file several hundred lines long just to deserialise basic JSON objects. This cost me a good hour that I could have spent elsewhere, such as the search functionality or improving the menu so you can delete and edit the result(s) you get from the search feature. The biggest feature that is missing is saving upon closing. I also regret the lack of comments. You can also only set the first name through the application. In this case, I think I should have started from the interface and worked my way towards the backend than the other way around, then I would have known what I was dealing with.