import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/project">
        <Translate contentKey="global.menu.entities.project" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/board">
        <Translate contentKey="global.menu.entities.board" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/column-entity">
        <Translate contentKey="global.menu.entities.columnEntity" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/issue">
        <Translate contentKey="global.menu.entities.issue" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
