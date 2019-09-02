# Address Book

This was a task assigned to me as a technical test. I am continuing work on it as I want to improve on certain things as a matter of practice, but if you want to see the code I submitted see [26c7c09](https://github.com/Protonull/AddressBook/commit/26c7c09a6c598daec89fef1b3cd94f4c651fde25).

## Specification

Your task will be to write a command line address book in your preferred language.

You will have three to four hours to complete the task. Once time is up, you should send us your code. You should also write an email afterwards to explain, if you had been given more time, what your intentions to improve the program would be.

While writing the program you should keep the following things in mind:

- Ensure all code is tested.
- Ensure the specification is being adhered to.

The following functionality should be implemented into your program:

- Ensure all inputs are appropriately validated.
- Method to add a person to the address book. Each person's record should include:
  - First name
  - Other names
  - Email address
  - Telephone number
  - Address, which should contain the following:
    - Street
    - Town
    - Country
- Method to remove a person from the address book.
- Method to search for a person in the address book. The user should be able to search via:
  - First name
  - Other names
  - Email address
  - Telephone number
  - Town
  - Country
- The program, on exit, should write the records to a file. The records should then be reloaded when the program is executed again.

For extra credit, your program should be able to handle a large number of entries. To do this, you will need to optimise the search. You should attempt this without using a library (write it from scratch).

## Submission

Upon completion, I had the following working:

- It compiled, which is always a plus.
- It had commands and menu screens that could be navigated.
- You could create new contact entries and add them to the address book.
- You could load an address book from a file.
- You could search for contacts in the address book.
- You could *scroll* through the address book for a particular contact.
- You could remove a contact from the address book.

That being said, there was plenty of missing features, such as:

- You could only define and edit a contact's first name. The commands to edit their other names or phone number had yet to be added.
- You could not search for contacts based on their address details.
- On exit, graceful or otherwise, the address book is not saved to a file.

## Installation and Usage

To install, run the following command in the root folder of the project

```
mvn clean package
```

You must have Java 8 and Maven installed.

To run, use the following command in the same folder after compilation

```
java -jar target/AddressBook.jar
```

The jar has the version truncated, so you will not need to change the command between versions.

## Development Log

### First Entry - Let's get started (14:06 BST)

I've just received the specification after a short phone call. The Address Book being command line based does placate some unease at creating a workable GUI as while Java has been my main language for almost two years now, the projects I've worked on have not used any sort of GUI. I could probably still make one given enough time, but time is limited and it being console based allows me to allocate that time elsewhere.

I received the email with the specification at 14:06 BST and have just under four hours to complete the task, so the final commit should at or before 18:06 BST.

Looking at the specification, I know that I will need to use an encoding library. It's possible to go without, perhaps relying on Java's `Serializable` interface and saving the raw binary into files, however to ensure easy debugging, I will likely choose JSON to store address entries in a readable format. This is somewhat of a cheat, and I will move away from it if I can, but I don't want to get stuck on reinventing the wheel of data encoding, resulting in a non-functional application at the deadline. If there is time remaining, I will begin the process of transitioning to a new, custom made system.

The specification also includes file saves on program exit. I will have to look into catching abrupt program exits, as the plugins I am used to making are designed around a `onDisable()` method that can gracefully handle deliberate shutdowns.

### Second Entry - Tick Tock (~16:10 BST)

It is now two hours into the task and I think the barebones of the application is coming along nicely. So far it has been creating data classes, which in Java can take awhile because of the standards of encapsulation. I have yet to do any of the console interactions, instead focusing more on the back end. The next hour will be dedicated to finishing the back end and the final hour will be more focused on the user interface.

I think time could have been saved if I had ruled out a different encoding system and committed to using JSON. I have gone with an interface and implementation style system which sunk around 20-30 minutes time as I contemplated designs that would be useful enough to include but generic enough to be universal between a JSON implementation and a custom implementation.

### Third Entry - Uh oh (~17:23 BST)

It is certain that I'm not going to complete this task in time.

### Fourth Entry - Hindsight (18:07 BST)

It's 18:07. I managed to jank some things into semi-functionality but there is much I wish I could improve on. I spent far too much time over-engineering the back end of the application. The biggest example of this is the deserialisation of JSON. In an attempt to make it as robust as possible, I spent far too much time type checking which ended up with a file several hundred lines long just to deserialise basic JSON objects. This cost me a good hour that I could have spent elsewhere, such as the search functionality or improving the menu so you can delete and edit the result(s) you get from the search feature. The biggest feature that is missing is saving upon closing. I also regret the lack of comments. You can also only set the first name through the application. In this case, I think I should have started from the interface and worked my way towards the backend than the other way around, then I would have known what I was dealing with.

## Post-Submission Development Log

This is perhaps less necessary as there are commits to show the changes and updates, however this will be where I put my general thoughts and direction.

### Cleaning Up

So far I have been working on creating a cleaner, more robust application without adding new features, at least no yet. The biggest example of this is the new Menu system. It was unfortunate that the submitted code included a public static field that is accessed and modified all around the place. I'm slowly replacing those with managers and APIs.