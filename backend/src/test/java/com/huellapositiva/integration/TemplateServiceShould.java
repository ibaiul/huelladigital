package com.huellapositiva.integration;

import com.huellapositiva.domain.model.entities.ContactPerson;
import com.huellapositiva.domain.model.valueobjects.EmailAddress;
import com.huellapositiva.domain.model.valueobjects.Id;
import com.huellapositiva.domain.model.valueobjects.ProposalRevisionEmail;
import com.huellapositiva.infrastructure.TemplateService;
import com.huellapositiva.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@Import(TestData.class)
class TemplateServiceShould {

    @Autowired
    private TestData testData;

    @Autowired
    private TemplateService templateService;

    @Test
    void check_revision_type_before_parsing() throws Exception {
        // GIVEN
        ProposalRevisionEmail proposalRevisionEmail = ProposalRevisionEmail.builder()
                .hasFeedback(true)
                .esalContactPerson(new ContactPerson(EmailAddress.from(TestData.DEFAULT_EMAIL), Id.newId()))
                .proposalId(Id.newId())
                .proposalURI(testData.createMockImageUrl().toURI())
                .build();


        // WHEN + THEN
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> templateService.getProposalRevisionWithFeedbackTemplate(proposalRevisionEmail));
    }
}
