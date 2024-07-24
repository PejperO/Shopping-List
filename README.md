# Shopping-List

## Overview
The application is designed to help users manage the expiration dates of various products in categories such as food, medicines, and cosmetics, aiming to reduce waste by reminding users to use products before they expire.

![Annotation 2024-04-30 015509](https://github.com/user-attachments/assets/0ba1031e-ec78-4b96-8345-da0f1798cc78)


## Features
- **Product List View:** Displays a list of products with filtering options based on category and expiration status (valid/expired).
  - Each item shows the product name, expiration date, category, and quantity (if specified).
  - The list is automatically sorted by expiration date, from shortest to longest.
  - Summary of the number of items displayed.
  - Long press to delete an item if it is still valid, with confirmation alert.
  - Button to add a new product.
 
- **Product Add/Edit Screen:**
  - Triggered by clicking the add button or selecting an item from the list.
  - Allows users to input or edit product details with save button.
  - Category selection via dropdown or radio buttons.
  - Expiration date input using a date picker.
  - Data validation to ensure no past dates, empty names, and correct quantity inputs.
  - Edit functionality without deleting and re-adding items.
 
## Installation
1. Clone the repository:
```git clone https://github.com/PejperO/Shopping-List.git```
2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.

## Usage
- Launch the application on your Android device.
- Use the main screen to view and manage your products.
- Click on the add button to input new products or select an existing product to edit its details.
- Long press on a product to delete it if it's still valid.
- The product list will be sorted by expiration date, and a summary of the items will be displayed at the top.

## What I Learned
- **Android Development:** Enhanced my skills in developing Android applications using native Android SDK tools.
- **Kotlin/Java Programming:** Improved my proficiency in Kotlin/Java, including best practices for clean and readable code.
- **User Interface Design:** Learned how to design and implement user interfaces using Android Views and RecyclerView.
- **Data Validation:** Gained knowledge in implementing data validation techniques to ensure the accuracy and reliability of user inputs.
- **Project Management:** Developed better project management skills, including planning, implementing, and testing application features.
