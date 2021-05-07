package com.lacuc.pets.ui

import com.lacuc.pets.ui.manage.animal.add.AddAnimalFragment
import com.lacuc.pets.ui.manage.animal.choose.ChooseAnimalFragment
import com.lacuc.pets.ui.manage.animal.detail.AnimalDetailFragment
import com.lacuc.pets.ui.manage.animal.detail.medical.AddMedicalFragment
import com.lacuc.pets.ui.manage.animal.detail.memo.AddMemoFragment
import com.lacuc.pets.ui.manage.group.choose.ChooseGroupFragment
import com.lacuc.pets.ui.manage.group.gallery.GalleryFragment
import com.lacuc.pets.ui.manage.group.gallery.detail.ImageDetailFragment
import com.lacuc.pets.ui.manage.group.gallery.save.SaveImageFragment
import com.lacuc.pets.ui.manage.group.info.UserProfileFragment
import com.lacuc.pets.ui.manage.group.member.ManageMemberFragment
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
    abstract fun getAddAnimalFragment(): AddAnimalFragment

    @ContributesAndroidInjector
    abstract fun getAnimalDetailFragment(): AnimalDetailFragment

    @ContributesAndroidInjector
    abstract fun getAddMedicalFragment(): AddMedicalFragment

    @ContributesAndroidInjector
    abstract fun getAddMemoFragment(): AddMemoFragment

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
}