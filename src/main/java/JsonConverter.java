import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides utility methods for converting between Task objects and their JSON
 * string representations.
 */
public class JsonConverter {

    /**
     * Converts a Task object to a JSON object string.
     *
     * @param task Task object to convert.
     * @return JSON object string representation of the Task.
     */
    public static String taskToJsonObject(Task task) {
        return "{" +
                "\"id\":" + task.getId() + "," +
                "\"description\":\"" + task.getDescription() + "\"," +
                "\"status\":\"" + task.getStatus() + "\"," +
                "\"createdAt\":\"" + task.getCreatedAt() + "\"," +
                "\"updatedAt\":\"" + task.getUpdatedAt() + "\"" +
                "}";
    }

    /**
     * Converts a list of Task objects to a JSON array string.
     *
     * @param tasks List of Task objects to convert.
     * @return JSON array string representation of the list of Task objects.
     */
    public static String tasksToJsonArray(List<Task> tasks) {
        StringBuilder jsonArray = new StringBuilder("[");

        // Iterate through the list of tasks to construct JSON array
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            jsonArray.append(taskToJsonObject(task));

            if (i < tasks.size() - 1) {
                jsonArray.append(",");
            }
        }

        return jsonArray.append("]").toString();
    }

    /**
     * Converts a JSON object string to a Task object.
     *
     * @param jsonObject JSON object string representing a single Task object.
     * @return Task object parsed from the JSON object string.
     */
    public static Task jsonObjectToTask(String jsonObject) {
        // Remove enclosing braces
        jsonObject = jsonObject.trim().substring(1, jsonObject.length() - 1);

        /*
        Split JSON object into key-value pairs, preserving commas within
        strings. This allows commas in task descriptions. This is achieved by
        splitting only on ',' outside quotes.
         */
        List<String> keyValuePairs = new ArrayList<>();
        StringBuilder currentKeyValuePair = new StringBuilder();
        boolean insideString = false;
        for (char character : jsonObject.toCharArray()) {
            if (character == '\"') {
                insideString = !insideString;
            }
            if (character == ',' && !insideString) {
                keyValuePairs.add(currentKeyValuePair.toString().trim());
                currentKeyValuePair.setLength(0);
            } else {
                currentKeyValuePair.append(character);
            }
        }
        keyValuePairs.add(currentKeyValuePair.toString().trim());

        /*
        Split key-value pairs into key and value, preserving colons within
        strings. This allows colons in task descriptions. This is achieved by
        splitting on the first occurrence of ':'.
        */
        Task task = new Task();
        for (String keyValuePair : keyValuePairs) {
            keyValuePair = keyValuePair.replace("\"", "");
            int colonIndex = keyValuePair.indexOf(":");
            String key = keyValuePair.substring(0, colonIndex).trim();
            String value = keyValuePair.substring(colonIndex + 1).trim();

            /*
            Populate Task fields using values parsed from corresponding JSON
            key-value pairs.
             */
            switch (key) {
                case "id":
                    task.setId(Integer.parseInt(value));
                    break;
                case "description":
                    task.setDescription(value);
                    break;
                case "status":
                    task.setStatus(TaskStatus.valueOf(value));
                    break;
                case "createdAt":
                    task.setCreatedAt(LocalDateTime.parse(value));
                    break;
                case "updatedAt":
                    task.setUpdatedAt(LocalDateTime.parse(value));
                    break;
            }
        }

        return task;
    }

    /**
     * Converts a JSON array string to a list of Task objects.
     *
     * @param jsonArray JSON array string representing a list of Task objects.
     * @return List of Task objects parsed from the JSON array string.
     */
    public static List<Task> jsonArrayToTasks(String jsonArray) {
        // Remove enclosing brackets
        jsonArray = jsonArray.trim().substring(1, jsonArray.length() - 1).trim();

        /*
        Split JSON array string into an actual array of JSON object strings,
        ignoring spaces in between.
         */
        String[] jsonObjects = jsonArray.split("(?<=})\\s*,\\s*(?=\\{)");

        // Convert each JSON object string to a Task object and add to the list
        List<Task> tasks = new ArrayList<>();
        for (String jsonObject : jsonObjects) {
            tasks.add(jsonObjectToTask(jsonObject));
        }

        return tasks;
    }
}
