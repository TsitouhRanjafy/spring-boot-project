# GOOGLE CLOUD TASK WITH SPRING-BOOT 3.5.12

## before start

**In Google Cloud console**

 - create the queue

```bash
gcloud tasks queues create queue-name --location=zone
```

 - get detail

```bash
gcloud tasks queues describe queue-name --location=zone
```
    
 - create a key for a service account Cloud Task Creator Role    

**Set up the environment**

```bash
export GOOGLE_APPLICATION_CREDENTIALS=/path/to/key.json
export GOOGLE_CLOUD_PROJECT=project-id
export GCP_LOCATION=zone-queue
export GCP_QUEUE_ID=queue-id
```

## start

```bash
./mvnw spring-boot:run
```

