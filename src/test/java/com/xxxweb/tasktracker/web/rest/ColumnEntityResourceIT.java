package com.xxxweb.tasktracker.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.xxxweb.tasktracker.IntegrationTest;
import com.xxxweb.tasktracker.domain.ColumnEntity;
import com.xxxweb.tasktracker.repository.ColumnEntityRepository;
import com.xxxweb.tasktracker.service.dto.ColumnEntityDTO;
import com.xxxweb.tasktracker.service.mapper.ColumnEntityMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ColumnEntityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ColumnEntityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/column-entities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ColumnEntityRepository columnEntityRepository;

    @Autowired
    private ColumnEntityMapper columnEntityMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restColumnEntityMockMvc;

    private ColumnEntity columnEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ColumnEntity createEntity(EntityManager em) {
        ColumnEntity columnEntity = new ColumnEntity()
            .name(DEFAULT_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return columnEntity;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ColumnEntity createUpdatedEntity(EntityManager em) {
        ColumnEntity columnEntity = new ColumnEntity()
            .name(UPDATED_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return columnEntity;
    }

    @BeforeEach
    public void initTest() {
        columnEntity = createEntity(em);
    }

    @Test
    @Transactional
    void createColumnEntity() throws Exception {
        int databaseSizeBeforeCreate = columnEntityRepository.findAll().size();
        // Create the ColumnEntity
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);
        restColumnEntityMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeCreate + 1);
        ColumnEntity testColumnEntity = columnEntityList.get(columnEntityList.size() - 1);
        assertThat(testColumnEntity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testColumnEntity.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testColumnEntity.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testColumnEntity.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testColumnEntity.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createColumnEntityWithExistingId() throws Exception {
        // Create the ColumnEntity with an existing ID
        columnEntityRepository.saveAndFlush(columnEntity);
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        int databaseSizeBeforeCreate = columnEntityRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restColumnEntityMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = columnEntityRepository.findAll().size();
        // set the field null
        columnEntity.setName(null);

        // Create the ColumnEntity, which fails.
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        restColumnEntityMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isBadRequest());

        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = columnEntityRepository.findAll().size();
        // set the field null
        columnEntity.setCreatedDate(null);

        // Create the ColumnEntity, which fails.
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        restColumnEntityMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isBadRequest());

        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = columnEntityRepository.findAll().size();
        // set the field null
        columnEntity.setCreatedBy(null);

        // Create the ColumnEntity, which fails.
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        restColumnEntityMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isBadRequest());

        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllColumnEntities() throws Exception {
        // Initialize the database
        columnEntityRepository.saveAndFlush(columnEntity);

        // Get all the columnEntityList
        restColumnEntityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(columnEntity.getId().toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    void getColumnEntity() throws Exception {
        // Initialize the database
        columnEntityRepository.saveAndFlush(columnEntity);

        // Get the columnEntity
        restColumnEntityMockMvc
            .perform(get(ENTITY_API_URL_ID, columnEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(columnEntity.getId().toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingColumnEntity() throws Exception {
        // Get the columnEntity
        restColumnEntityMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingColumnEntity() throws Exception {
        // Initialize the database
        columnEntityRepository.saveAndFlush(columnEntity);

        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();

        // Update the columnEntity
        ColumnEntity updatedColumnEntity = columnEntityRepository.findById(columnEntity.getId()).get();
        // Disconnect from session so that the updates on updatedColumnEntity are not directly saved in db
        em.detach(updatedColumnEntity);
        updatedColumnEntity
            .name(UPDATED_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(updatedColumnEntity);

        restColumnEntityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, columnEntityDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isOk());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
        ColumnEntity testColumnEntity = columnEntityList.get(columnEntityList.size() - 1);
        assertThat(testColumnEntity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testColumnEntity.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testColumnEntity.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testColumnEntity.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testColumnEntity.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingColumnEntity() throws Exception {
        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();
        columnEntity.setId(UUID.randomUUID());

        // Create the ColumnEntity
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColumnEntityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, columnEntityDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchColumnEntity() throws Exception {
        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();
        columnEntity.setId(UUID.randomUUID());

        // Create the ColumnEntity
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restColumnEntityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamColumnEntity() throws Exception {
        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();
        columnEntity.setId(UUID.randomUUID());

        // Create the ColumnEntity
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restColumnEntityMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateColumnEntityWithPatch() throws Exception {
        // Initialize the database
        columnEntityRepository.saveAndFlush(columnEntity);

        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();

        // Update the columnEntity using partial update
        ColumnEntity partialUpdatedColumnEntity = new ColumnEntity();
        partialUpdatedColumnEntity.setId(columnEntity.getId());

        partialUpdatedColumnEntity.name(UPDATED_NAME).createdDate(UPDATED_CREATED_DATE).lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restColumnEntityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedColumnEntity.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedColumnEntity))
            )
            .andExpect(status().isOk());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
        ColumnEntity testColumnEntity = columnEntityList.get(columnEntityList.size() - 1);
        assertThat(testColumnEntity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testColumnEntity.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testColumnEntity.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testColumnEntity.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testColumnEntity.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateColumnEntityWithPatch() throws Exception {
        // Initialize the database
        columnEntityRepository.saveAndFlush(columnEntity);

        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();

        // Update the columnEntity using partial update
        ColumnEntity partialUpdatedColumnEntity = new ColumnEntity();
        partialUpdatedColumnEntity.setId(columnEntity.getId());

        partialUpdatedColumnEntity
            .name(UPDATED_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restColumnEntityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedColumnEntity.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedColumnEntity))
            )
            .andExpect(status().isOk());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
        ColumnEntity testColumnEntity = columnEntityList.get(columnEntityList.size() - 1);
        assertThat(testColumnEntity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testColumnEntity.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testColumnEntity.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testColumnEntity.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testColumnEntity.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingColumnEntity() throws Exception {
        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();
        columnEntity.setId(UUID.randomUUID());

        // Create the ColumnEntity
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColumnEntityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, columnEntityDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchColumnEntity() throws Exception {
        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();
        columnEntity.setId(UUID.randomUUID());

        // Create the ColumnEntity
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restColumnEntityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamColumnEntity() throws Exception {
        int databaseSizeBeforeUpdate = columnEntityRepository.findAll().size();
        columnEntity.setId(UUID.randomUUID());

        // Create the ColumnEntity
        ColumnEntityDTO columnEntityDTO = columnEntityMapper.toDto(columnEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restColumnEntityMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(columnEntityDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ColumnEntity in the database
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteColumnEntity() throws Exception {
        // Initialize the database
        columnEntityRepository.saveAndFlush(columnEntity);

        int databaseSizeBeforeDelete = columnEntityRepository.findAll().size();

        // Delete the columnEntity
        restColumnEntityMockMvc
            .perform(delete(ENTITY_API_URL_ID, columnEntity.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ColumnEntity> columnEntityList = columnEntityRepository.findAll();
        assertThat(columnEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
