import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ColumnEntity from './column-entity';
import ColumnEntityDetail from './column-entity-detail';
import ColumnEntityUpdate from './column-entity-update';
import ColumnEntityDeleteDialog from './column-entity-delete-dialog';

const ColumnEntityRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ColumnEntity />} />
    <Route path="new" element={<ColumnEntityUpdate />} />
    <Route path=":id">
      <Route index element={<ColumnEntityDetail />} />
      <Route path="edit" element={<ColumnEntityUpdate />} />
      <Route path="delete" element={<ColumnEntityDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ColumnEntityRoutes;
