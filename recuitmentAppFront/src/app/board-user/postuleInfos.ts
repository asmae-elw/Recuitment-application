import {User} from "../login/user";

export interface PostuleInfos {
  id: number;
  cv: string;
  email: string;
  linkdenUrl: string;
  githubUrl: string;
  societe: string;
  domaineDetude: string;
  condidatPostule: User;

}
