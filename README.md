# TaskTracker

TaskTracker is a lightweight command-line application for tracking and managing tasks. It offers functionality to add, update, delete and list tasks.

TaskTracker is designed according to the guidelines of [this project](https://roadmap.sh/projects/task-tracker).

## Installation

1. Ensure you have [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) installed.


2. Navigate to the directory where you want to install the application and clone or download this repository:

```bash
git clone https://github.com/jesuscastaner/tasktracker.git
```

3. Navigate to the project directory and compile:

```bash
cd tasktracker
javac -d out src/main/java/*.java
```

4. Add the following alias to your shell configuration file (`~/.bashrc` for bash, `~/.zshrc` for zsh), replacing `.../` with the actual path to the project directory:

```bash
echo 'alias tasktracker="cd .../tasktracker/out && java -cp out Main"' >> ~/.bashrc
```

5. Reload the configuration file (`~/.bashrc` for bash, `~/.zshrc` for zsh):

```bash
source ~/.bashrc
```

## Usage

Add a new task:

```markdown
tasktracker add "<description>"
```

Update the description of a task:

```markdown
tasktracker update <id> "<description>"
```

Delete one or more tasks:

```markdown
delete <id1> <id2> ... <idN>
```

Change the status of one or more tasks:

```markdown
tasktracker mark-todo <id1> <id2> ... <idN>
tasktracker mark-in-progress <id1> <id2> ... <idN>
tasktracker mark-done <id1> <id2> ... <idN>
```

List all tasks:

```markdown
tasktracker list
```

List tasks filtered by status:

```markdown
tasktracker list todo
tasktracker list in-progress
tasktracker list done
```

Display help:

```markdown
tasktracker help
```

## Uninstallation

1. Remove the alias from your shell configuration file (`~/.bashrc` for bash, `~/.zshrc` for zsh):

```bash
sed -i '/alias tasktracker=/d' ~/.bashrc
```

2. Reload the configuration file (`~/.bashrc` for bash, `~/.zshrc` for zsh):

```bash
source ~/.bashrc
```

3. Navigate to the directory where you installed the application and delete it:

```bash
rm -rf tasktracker
```

## How to contribute

Please, fork this repository and submit your changes via a [pull request](https://github.com/jesuscastaner/tasktracker/pulls).

If you encounter any issues while using the application, feel free to [open an issue](https://github.com/jesuscastaner/tasktracker/issues).
