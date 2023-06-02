import dayjs from 'dayjs';
import { IProject } from 'app/shared/model/project.model';

export interface IColumnEntity {
  id?: string;
  name?: string;
  createdDate?: string;
  createdBy?: string;
  lastModifiedBy?: string | null;
  lastModifiedDate?: string | null;
  project?: IProject | null;
}

export const defaultValue: Readonly<IColumnEntity> = {};
