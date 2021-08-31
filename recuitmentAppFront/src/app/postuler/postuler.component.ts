import {HttpErrorResponse} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {PostuleInfos} from '../board-user/postuleInfos';
import {User} from '../login/user';
import {Offre} from '../offre/offre';
import {OffreService} from '../_services/offre.service';
import {PostuleInfosService} from '../_services/postuleInfosService';

@Component({
  selector: 'app-postuler',
  templateUrl: './postuler.component.html',
  styleUrls: ['./postuler.component.css']
})
export class PostulerComponent implements OnInit {
  public offres: Offre[];
  public editOffre: Offre;
  public deleteOffre: Offre;

  //public postuleInfosService: PostuleInfosService;
  public infos: PostuleInfos[];
  currentUser: User;

  constructor(private offreService: OffreService, private postuleInfosService: PostuleInfosService) {
  }

  ngOnInit() {
    this.getOffres();
    this.currentUser = JSON.parse(window.sessionStorage.getItem("auth-user"));

  }

  public getOffres(): void {
    this.offreService.getOffres().subscribe(
      (response: Offre[]) => {
        this.offres = response;
        console.log(this.offres);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(postuleInfos: PostuleInfos, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'addPostuleInfos') {
      button.setAttribute('data-target', '#addPostuleInfosModal');
    }
    container.appendChild(button);
    button.click();
  }

  // ajouter pour le button postuler

  public onAddPostuleInfos(addForm: NgForm): void {
    document.getElementById('add-postuleInfos-form').click();

    console.log("hello1 !", {...addForm.value, condidatPostule: this.currentUser});

    this.postuleInfosService.addPostuleInfos({...addForm.value, condidatPostule: this.currentUser}).subscribe(
      (response: PostuleInfos) => {
        console.log("hello!", response);
        this.getPostuleInfos();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
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
