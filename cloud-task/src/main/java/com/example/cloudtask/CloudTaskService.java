package com.example.cloudtask;

// import com.google.api.client.util.Value;
import org.springframework.beans.factory.annotation.Value;
import com.google.cloud.tasks.v2.*;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class CloudTaskService {
    @Value("${gcp.location.queue}")
    private String locationId;

    @Value("${gcp.queue-id}")
    private String queueId;

    public void createHttpTask() throws Exception {
        // TODO(developer): Replace these variables before running the sample.
        String projectId = System.getenv("GOOGLE_CLOUD_PROJECT");
        createTask(projectId, locationId, queueId);
    }

    // Create a task with an HTTP target using the Cloud Tasks client.
    public void createTask(String projectId, String locationId, String queueId) throws Exception {
        try (CloudTasksClient client = CloudTasksClient.create()){
            String url = "https://example.com/tasks/hello";
            String payload = "Hello, World!";
            // Construct the fully qualified queue name.
            String queuePath = QueueName.of(projectId, locationId, queueId).toString();

            // Exécuter dans 10 minutes
            Instant executeAt = Instant.now().plus(10, ChronoUnit.MINUTES);

            /* *
             * Ou avec une data précise
             * LocalDateTime executeAt = LocalDateTime.of(2026, 4, 1, 9, 0);
             * long epochSeconds = executeAt.toEpochSecond(ZoneOffset.UTC);
             * com.google.protobuf.Timestamp.newBuilder()
             *      .setSeconds(epochSeconds)
             *      .build()
             * */

            // Construct the task body.
            Task.Builder taskBuilder =
                    Task.newBuilder()
                            .setScheduleTime(
                                    com.google.protobuf.Timestamp.newBuilder()
                                            .setSeconds(executeAt.getEpochSecond())
                                            .build()
                            )
                            .setHttpRequest(
                                    HttpRequest.newBuilder()
                                            .setUrl(url)
                                            .setHttpMethod(HttpMethod.POST)
                                            .setBody(ByteString.copyFrom(payload, Charset.defaultCharset()))
                                            .build()
                            );

            Task task = client.createTask(queuePath, taskBuilder.build());
            System.out.println("Task created: " + task.getName());
        }
    }
}
