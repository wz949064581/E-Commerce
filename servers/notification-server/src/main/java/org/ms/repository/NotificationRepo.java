package org.ms.repository;

import org.ms.entityAndDTO.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepo extends MongoRepository<Notification, String> {


}
