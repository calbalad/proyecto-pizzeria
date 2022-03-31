package com.pizzaiolo.authorization.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pizzaiolo.authorization.BaseIT;
import com.pizzaiolo.authorization.models.dtos.CreateUserDto;
import com.pizzaiolo.authorization.models.entities.Coordinates;
import com.pizzaiolo.authorization.models.entities.User;
import com.pizzaiolo.authorization.models.response.InvalidDataResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;

@Ignore("not yet ready , Please ignore.")
public class RegisterUserIT extends BaseIT {
  private static final String ENDPOINT = "/api/v1/auth/register";

  @Autowired
  private TestRestTemplate restTemplate;

  @MockBean
  private JavaMailSender mailSender;

  @Captor
  ArgumentCaptor<MimeMessage> mimeMessageCaptor;
  
  @DisplayName("Register - Register user successfully")
  @Disabled("not yet ready, Please ignore.")
  public void testRegisterUserSuccess() {
    // GIVEN
    CreateUserDto createUserDto = new CreateUserDto()
        .setFirstName("Json")
        .setLastName("Delo")
        .setEmail("jsondelo@email.com")
        .setPassword("password")
        .setConfirmPassword("password")
        .setCoordinates(new Coordinates(4.4545f, 9.73248f))
        .setGender("M")
        .setTimezone("Europe/Paris");

    HttpEntity<CreateUserDto> request = new HttpEntity<>(createUserDto, testUtility.createHeaders());

    /*Object registerResponse = restTemplate.postForEntity(ENDPOINT, request, Object.class);
    System.out.println(registerResponse);*/

    // WHEN
    when(mailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

    doNothing().when(mailSender).send(mimeMessageCaptor.capture());

    ResponseEntity<User> registerResponse = restTemplate.postForEntity(ENDPOINT, request, User.class);

    System.out.println(registerResponse);

    // THEN
    assertThat(registerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    verify(mailSender, times(1)).send(mimeMessageCaptor.capture());

    User user = Objects.requireNonNull(registerResponse.getBody());

    try {
      Address[] addresses = mimeMessageCaptor.getValue().getAllRecipients();

      assertThat(addresses).hasSize(1);
      assertThat(addresses[0].toString()).isEqualTo(createUserDto.getEmail());
    } catch (MessagingException e) {
      e.printStackTrace();
    }

    assertThat(user.getEmail()).isEqualTo(createUserDto.getEmail());
    assertThat(user.getId()).isNotNull();
  }
}
