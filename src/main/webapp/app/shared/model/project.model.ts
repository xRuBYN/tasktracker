import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';

export interface IProject {
  id?: string;
  name?: string;
  createdDate?: string;
  createdBy?: string;
  lastModifiedBy?: string | null;
  lastModifiedDate?: string | null;
  user?: IUser | null;
  users?: IUser[] | null;
}

export const defaultValue: Readonly<IProject> = {};
