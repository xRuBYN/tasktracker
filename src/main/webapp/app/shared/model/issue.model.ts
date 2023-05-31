import dayjs from 'dayjs';
import { IColumnEntity } from 'app/shared/model/column-entity.model';
import { IUser } from 'app/shared/model/user.model';
import { PriorityType } from 'app/shared/model/enumerations/priority-type.model';

export interface IIssue {
  id?: string;
  name?: string;
  description?: string | null;
  priority?: PriorityType | null;
  createdDate?: string;
  createdBy?: string;
  lastModifiedBy?: string | null;
  lastModifiedDate?: string | null;
  column?: IColumnEntity | null;
  assigned?: IUser | null;
}

export const defaultValue: Readonly<IIssue> = {};
