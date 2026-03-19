Redesign CRC Cards:

Class: Order
Responsibilities:
- Hold and encapsulate order data (customer name, email, item, price)
- Provide controlled access to its data through getters
- Represent a single, coherent order abstraction
Collaborators: None

Class: TaxCalculator
Responsibilities:
- Compute the tax amount for a given price
- Compute the total price including tax
Collaborators: Order

Class: DiscountService
Responsibilities:
- Determine whether an order qualifies for a discount
- Apply the appropriate discount to a given total
Collaborators: Order

Class: OrderRepository
Responsibilities:
- Persist a completed order record to storage
- Encapsulate all knowledge of how and where orders are stored
Collaborators: Order

Class: EmailService
Responsibilities:
- Send a confirmation email to the customer associated with an order
Collaborators: Order

Class: ReceiptPrinter
Responsibilities:
- Format and print a receipt for a completed order, including the final total
Collaborators: Order

Class: ActivityLogger
Responsibilities:
- Log that an order was processed and record the time at which it occurred
Collaborators: Order

Class: OrderProcessor
Responsibilities:
- Coordinate the end-to-end order processing workflow by delegating to collaborators in the correct sequence
- Accept all dependencies from the outside rather than constructing them internally
Collaborators: Order, TaxCalculator, DiscountService, OrderRepository, EmailService, ReceiptPrinter, ActivityLogger

	