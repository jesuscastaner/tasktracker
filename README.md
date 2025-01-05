# TaskTracker

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

Delete a task:

```markdown
delete <id>
```

Mark a task as to do, in progress or done:"

```markdown
tasktracker mark-todo
tasktracker mark-in-progress
tasktracker mark-done
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
