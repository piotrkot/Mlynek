Shop - programming task

1. Technology choice
    * Frontend - Bootstrap as the popular front-end framework. Mustache templating
  system being default from Dropwizard. Additionally little custom Javascript/JQuery
  for customization.
    * Backend - Dropwizard as the easy and light-weight Java framework. It is very simple
  to get started. Provides all I need for the task with no deployment and easily debuggable
  and testable.
2. For the persistence layer I would go for the JDBI library. It is a convenient way
 to operate with relational data. ORM frameworks could also be an alternative however
 with time they lead to advanced session management and ease of misuse. For small projects
 I often take a risk with new tools.
3. Task took 2.5 days, where the first day I needed to recall Dropwizard, Bootstrap
 and JQuery but all was running. On the second day, I did some refactoring, renaming,
 and added tests. Remaining time was used to polish the details and add more tests.
4. If items are updated daily from a REST API, I would add new Managed implementation
 for the Dropwizard environment lifecycle. The class would start a Thread which daily
 synchronizes the MemoryStore.