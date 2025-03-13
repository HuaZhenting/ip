# Huke Chatbot User Guide

Welcome to Huke! 
This task management system helps you manage your tasks efficiently. 
You can add tasks, mark and unmark them, delete them, and even search for specific tasks. 
The available task types are To-Do, Deadline, and Event.

## Adding a todo
To add a to-do task, use the todo command followed by a description.
todo <description>
Example: todo play
Expected Outcome: 
    Great! I've added this task for you:
    [T][ ] play

## Adding a deadline
To add a deadline task, 
use the deadline command followed by the description and /by for the deadline time.
deadline <description> /by <deadline_time>
Example: deadline Submit assignment /by 15/3/2024 2359
Expected Outcome: 
    Great! I've added this task for you:
    [D][ ] Submit assignment (by: Mar 15 2024 23:59)

## Adding a event
To add an event task, 
use the event command with /from for the start time and /to for the end time.
event <description> /from <start_time> /to <end_time>
Example: event Meeting with client /from 20/6/2025 0930 /to 20/6/2025 1200
Expected Outcome: 
    Great! I've added this task for you:
    [E][ ] Meeting with client (from: Jun 20 2025 09:30 to: Jun 20 2025 12:00)


## Listing tasks
To see all your tasks, use the list command. 
It will show you a numbered list of tasks along with their type and status.
Example: list
Expected Outcome:
    This is your list:
    1 [T][ ] 1
    2 [D][ ] Submit assignment (by: Mar 15 2024 23:59)
    3 [E][ ] Coding workshop (from: Jun 20 2025 09:30 to: Jun 20 2025 12:00)
    4 [E][ ] Meeting with client (from: Jun 20 2025 09:30 to: Jun 20 2025 12:00)


## Marking Tasks as Done
To mark a task as completed, use the mark command followed by the task number.
mark <task_number>
Example: mark 5
Expected Outcome:
    I've helped you mark the task:
    [T][X] play

## Unmarking Tasks
To unmark a task, use the unmark command followed by the task number.
unmark <task_number>
Example: unmark 5
Expected Outcome:
    I've helped you unmark the task:
    [T][ ] play

## Deleting Tasks
To delete a task, use the delete command followed by the task number.
delete <task_number>
Example: delete 6
Expected Outcome:
    Finished a task? Great!
    I've helped you remove it from your list:
    [D][ ] Submit assignment (by: Mar 15 2024 23:59)

## Finding Tasks
To search for a task, use the find command followed by a keyword in the description.
find <keyword>
Example: find workshop
Expected Outcome:
    Here's what I found:
    [E][ ] Coding workshop (from: Jun 20 2025 09:30 to: Jun 20 2025 12:00)
