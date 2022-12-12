import json
import csv
import os

# Prompt the user to enter the name of the directory containing the JSON files
directory = input('Enter the name of the directory containing the JSON files: ')

# Get a list of all the JSON file names in the directory
file_names = os.listdir(directory)

# Prompt the user to enter the name of the CSV file to be created
csv_file_name = input('Enter the name of the CSV file to be created: ')

# Open the CSV file for writing
with open(csv_file_name, 'w') as csv_file:
    # Create a CSV writer
    writer = csv.DictWriter(csv_file, fieldnames=[], lineterminator='\n')

    iteration = 1
    # Iterate over the list of file names
    for file_name in file_names:
        # Check if the file is a JSON file
        if file_name.endswith('.json'):
            # Open the JSON file
            with open(os.path.join(directory, file_name)) as json_file:
                # Load the data from the file
                data = json.load(json_file)[0]["QUERY PLAN"][0]

            data.update({"sample": iteration})

            # If this is the first file, write the headers
            if not writer.fieldnames:
                writer.fieldnames = data.keys()
                writer.writeheader()

            # Iterate over the data and write it to the CSV file, skipping the root element
            writer.writerow(data)

        iteration += 1