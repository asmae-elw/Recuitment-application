import {HttpErrorResponse} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {PostuleInfosService} from '../_services/postuleInfosService';
import {PostuleInfos} from './postuleInfos';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {


  public infos: PostuleInfos[];


  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string;
  tokenStorageService: any;

  constructor(private postuleInfosService: PostuleInfosService) {
  }

  ngOnInit() {
    this.getPostuleInfos();
  }


  public getPostuleInfos(): void {
    this.postuleInfosService.getPostuleInfos().subscribe(
      (response: PostuleInfos[]) => {
        this.infos = response;
        console.log(this.infos);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
