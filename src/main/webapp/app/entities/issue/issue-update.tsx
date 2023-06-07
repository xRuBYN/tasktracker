import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IColumnEntity } from 'app/shared/model/column-entity.model';
import { getEntities as getColumnEntities } from 'app/entities/column-entity/column-entity.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IIssue } from 'app/shared/model/issue.model';
import { PriorityType } from 'app/shared/model/enumerations/priority-type.model';
import { getEntity, updateEntity, createEntity, reset } from './issue.reducer';

export const IssueUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const columnEntities = useAppSelector(state => state.columnEntity.entities) || [];
  const users = useAppSelector(state => state.userManagement.users) || [];
  const issueEntity = useAppSelector(state => state.issue.entity);
  const loading = useAppSelector(state => state.issue.loading);
  const updating = useAppSelector(state => state.issue.updating);
  const updateSuccess = useAppSelector(state => state.issue.updateSuccess);
  const priorityTypeValues = Object.keys(PriorityType);

  const handleClose = () => {
    navigate('/issue' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getColumnEntities({}));
    dispatch(getUsers({}));
  }, [columnEntities]);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...issueEntity,
      ...values,
      column: columnEntities.find(it => it.id.toString() === values.column.toString()),
      assigned: users.find(it => it.id.toString() === values.assigned.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdDate: displayDefaultDateTime(),
          lastModifiedDate: displayDefaultDateTime(),
        }
      : {
          priority: 'LOWEST',
          ...issueEntity,
          createdDate: convertDateTimeFromServer(issueEntity.createdDate),
          lastModifiedDate: convertDateTimeFromServer(issueEntity.lastModifiedDate),
          column: issueEntity?.column?.id,
          assigned: issueEntity?.assigned?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tasktrackerApp.issue.home.createOrEditLabel" data-cy="IssueCreateUpdateHeading">
            <Translate contentKey="tasktrackerApp.issue.home.createOrEditLabel">Create or edit a Issue</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="issue-id"
                  label={translate('tasktrackerApp.issue.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tasktrackerApp.issue.name')}
                id="issue-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  minLength: { value: 3, message: translate('entity.validation.minlength', { min: 3 }) },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('tasktrackerApp.issue.description')}
                id="issue-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                label={translate('tasktrackerApp.issue.priority')}
                id="issue-priority"
                name="priority"
                data-cy="priority"
                type="select"
              >
                {priorityTypeValues.map(priorityType => (
                  <option value={priorityType} key={priorityType}>
                    {translate('tasktrackerApp.PriorityType.' + priorityType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('tasktrackerApp.issue.createdDate')}
                id="issue-createdDate"
                name="createdDate"
                data-cy="createdDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tasktrackerApp.issue.createdBy')}
                id="issue-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('tasktrackerApp.issue.lastModifiedBy')}
                id="issue-lastModifiedBy"
                name="lastModifiedBy"
                data-cy="lastModifiedBy"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('tasktrackerApp.issue.lastModifiedDate')}
                id="issue-lastModifiedDate"
                name="lastModifiedDate"
                data-cy="lastModifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="issue-column"
                name="column"
                data-cy="column"
                label={translate('tasktrackerApp.issue.column')}
                type="select"
              >
                <option value="" key="0" />
                {columnEntities.length > 0 && (
                  <ValidatedField
                    id="issue-column"
                    name="column"
                    data-cy="column"
                    label={translate('tasktrackerApp.issue.column')}
                    type="select"
                  >
                    <option value="" key="0" />
                    {Array.isArray(columnEntities) &&
                      columnEntities.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))}
                  </ValidatedField>
                )}
              </ValidatedField>
              <ValidatedField
                id="issue-assigned"
                name="assigned"
                data-cy="assigned"
                label={translate('tasktrackerApp.issue.assigned')}
                type="select"
              >
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/issue" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default IssueUpdate;
