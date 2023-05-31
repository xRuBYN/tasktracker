import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './column-entity.reducer';

export const ColumnEntityDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const columnEntityEntity = useAppSelector(state => state.columnEntity.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="columnEntityDetailsHeading">
          <Translate contentKey="tasktrackerApp.columnEntity.detail.title">ColumnEntity</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="tasktrackerApp.columnEntity.id">Id</Translate>
            </span>
          </dt>
          <dd>{columnEntityEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tasktrackerApp.columnEntity.name">Name</Translate>
            </span>
          </dt>
          <dd>{columnEntityEntity.name}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="tasktrackerApp.columnEntity.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            {columnEntityEntity.createdDate ? (
              <TextFormat value={columnEntityEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="tasktrackerApp.columnEntity.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{columnEntityEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedBy">
              <Translate contentKey="tasktrackerApp.columnEntity.lastModifiedBy">Last Modified By</Translate>
            </span>
          </dt>
          <dd>{columnEntityEntity.lastModifiedBy}</dd>
          <dt>
            <span id="lastModifiedDate">
              <Translate contentKey="tasktrackerApp.columnEntity.lastModifiedDate">Last Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {columnEntityEntity.lastModifiedDate ? (
              <TextFormat value={columnEntityEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="tasktrackerApp.columnEntity.board">Board</Translate>
          </dt>
          <dd>{columnEntityEntity.board ? columnEntityEntity.board.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/column-entity" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/column-entity/${columnEntityEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ColumnEntityDetail;
