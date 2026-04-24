Name: Amaya Keys

Heuristic 1:
Name: H3.2 Avoid God Classes
Do not create god classes/objects in your system.

Explanation:
This heuristic improves maintainability and readability because it doesn't centralize all knowledge into a single class and create tight coupling between components. For example, in class we discussed this heuristic in relation to Arthur's home heating system. The original HeatFlowRegulator class performed everything, including reading the actual and desired temperature, performing logic on the data and then acting on the furnace - it had to inspect much of the private data about the room. The improved version with the removal of the god class restored encapsulation, localized changes, and made HeatFlowRegulator only an orchestrator.

Heuristic 2:
Name: H3.7 Eliminate Irrelevant Classes
Eliminate irrelevant classes from your design.

Explanation:
This heuristic improves maintainability and readability by not making the developer have to question what a certain class does (less mental overhead) and adds more maintenance responsibilities when unnecessary. In lecture, we connected this to the proliferation of classes problem, which discusses taking the object-oriented design model too far and creating multiple tiny classes that don't do anything meaningful. This is almost the opposite of the God class problem (where the class does too much) in the sense that these types of classes do too little.

Heuristic 3: 
Name: H6.1 If you have an example of multiple inheritance in your design, assume you have made a mistake and prove otherwise.

Explanation:
This heuristic improves maintainability because it takes away the structural problems that multiple inheritances can potentially introduce such as ambiguity or duplicate inherited states. Multiple inheritances can get messy, hard to reason about and dangerous to modify without breaking something. In lecture, we discussed some rules for how to properly implement inheritance such as ensuring there are genuine "is-a" relationships and not misusing inheritance to reuse code rather than for true specializations.