package com.friendtimes.user.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserModule {
    private String user_name;
    private String module_id;
}
