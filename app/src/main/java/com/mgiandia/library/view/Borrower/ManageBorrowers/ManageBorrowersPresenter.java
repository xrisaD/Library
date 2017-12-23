package com.mgiandia.library.view.Borrower.ManageBorrowers;

import java.util.ArrayList;
import java.util.List;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.util.Quadruple;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageBorrowersPresenter
{
    private ManageBorrowersView view;
    private BorrowerDAO borrowers;

    /**
     * Επιστρέφει τα δεδομένα για μία λίστα από δανειζομένους.
     * @param borrowers Οι δανειζόμενοι στους οποίους θα επιστραφούν τα δεδομένα
     * @return Μία λίστα με τις λεπτομέρειες των δανειζομένων
     */
    private List<Quadruple> createDataSource(List<Borrower> borrowers)
    {
        List<Quadruple> triplets = new ArrayList<>();

        for(Borrower borrower : borrowers)
            triplets.add(new Quadruple(borrower.getBorrowerNo(), borrower.getFirstName(), borrower.getLastName(), "Κωδικός: "+borrower.getBorrowerNo()+". Σύνολο δανισμών "+borrower.getLoans().size()));

        return triplets;
    }

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param borrowers Ένα instance του borrower
     */
    public ManageBorrowersPresenter(ManageBorrowersView view, BorrowerDAO borrowers)
    {
        this.view = view;
        this.borrowers = borrowers;

        onLoadSource();
    }

    /**
     * Μεταφέρει τον χρήστη στο activity BorrowerDetailsActivity
     * όταν γίνει click πάνω στον δανειζόμενο με id uid.
     * @param uid To μοναδικό id του δανειζόμενου
     */
    void onClickItem(int uid)
    {
        if(view.shouldLoadLoansOnClick())
            view.clickItemLoans(uid);
        else if(view.shouldLoadReturnsOnClick())
            view.clickItemReturns(uid);
        else
            view.clickItem(uid);
    }

    /**
     * Ξεκινάει το activity AddEditBorrowerActivity
     */
    void onStartAddNew()
    {
        view.startAddNew();
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param value Το περιεχόμενο που θα εμφανιστεί
     */
    void onShowToast(String value)
    {
        view.showToast(value);
    }

    /**
     * Φορτώνει την λίστα με τους δανειζομένους.
     */
    void onLoadSource()
    {
        view.loadSource(createDataSource(borrowers.findAll()));
    }
}