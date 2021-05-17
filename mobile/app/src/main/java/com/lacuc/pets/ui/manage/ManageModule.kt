package com.lacuc.pets.ui.manage

import com.lacuc.pets.ui.manage.animal.choose.ChooseAnimalFragment
import com.lacuc.pets.ui.manage.animal.detail.AnimalDetailFragment
import com.lacuc.pets.ui.manage.animal.detail.medical.AddMedicalFragment
import com.lacuc.pets.ui.manage.animal.detail.memo.SaveMemoFragment
import com.lacuc.pets.ui.manage.animal.save.SaveAnimalFragment
import com.lacuc.pets.ui.manage.group.choose.ChooseGroupFragment
import com.lacuc.pets.ui.manage.group.gallery.GalleryFragment
import com.lacuc.pets.ui.manage.group.gallery.detail.ImageDetailFragment
import com.lacuc.pets.ui.manage.group.gallery.save.SaveImageFragment
import com.lacuc.pets.ui.manage.group.info.UserProfileFragment
import com.lacuc.pets.ui.manage.group.item.ItemListFragment
import com.lacuc.pets.ui.manage.group.member.ManageMemberFragment
import com.lacuc.pets.ui.manage.group.member.invite.InviteMemberFragment
import com.lacuc.pets.ui.manage.group.save.SaveGroupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ManageModule {
    @ContributesAndroidInjector
    abstract fun getChooseGroupFragment(): ChooseGroupFragment

    @ContributesAndroidInjector
    abstract fun getSaveGroupFragment(): SaveGroupFragment

    @ContributesAndroidInjector
    abstract fun getChooseAnimalFragment(): ChooseAnimalFragment

    @ContributesAndroidInjector
    abstract fun getAddAnimalFragment(): SaveAnimalFragment

    @ContributesAndroidInjector
    abstract fun getAnimalDetailFragment(): AnimalDetailFragment

    @ContributesAndroidInjector
    abstract fun getAddMedicalFragment(): AddMedicalFragment

    @ContributesAndroidInjector
    abstract fun getAddMemoFragment(): SaveMemoFragment

    @ContributesAndroidInjector
    abstract fun getUserProfileFragment(): UserProfileFragment

    @ContributesAndroidInjector
    abstract fun getManageMemberFragment(): ManageMemberFragment

    @ContributesAndroidInjector
    abstract fun getGalleryFragment(): GalleryFragment

    @ContributesAndroidInjector
    abstract fun getSaveImageFragment(): SaveImageFragment

    @ContributesAndroidInjector
    abstract fun getImageDetailFragment(): ImageDetailFragment

    @ContributesAndroidInjector
    abstract fun getItemListFragment(): ItemListFragment

    @ContributesAndroidInjector
    abstract fun getInviteMemberFragment(): InviteMemberFragment
}