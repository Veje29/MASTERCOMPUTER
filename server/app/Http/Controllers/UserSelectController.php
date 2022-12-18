<?php

namespace App\Http\Controllers;

use App\Models\UserPackage;
use Illuminate\Http\Request;
use Illuminate\Http\Response;

class UserSelectController extends Controller
{
    public function getPackage() {
        $userPackage = UserPackage::firstWhere("user_id", auth()->user()->id);

        if(!$userPackage) {
            return response()->json([
                "message" => "User package not found.",
                "user_package" => null
            ], Response::HTTP_OK);
        }

        $userPackage = UserPackage::firstWhere("user_id", auth()->user()->id)->with(["processor", "vga", "motherboard", "power_supply"])->get()->first();

        return response()->json([
            "message" => "User package successfully fetched.",
            "user_package" => $userPackage
        ], Response::HTTP_CREATED);
    }

    public function changeProfilePicture(Request $request) {
        $request->validate([
            // "image" => "file|required|mimes:jpg,png,jpeg"
            "image" => "string|required"
        ]);

        $path = "img/users/profile_pictures/".auth()->user()->id.".jpg";

        $ifp = fopen(public_path($path), 'wb');
        fwrite($ifp, base64_decode($request->image));
        fclose($ifp);

        $user = auth()->user();
        $user->photo_profile_path = $path;
        $user->save();

        return response()->json([
            "message" => "Photo profile successfully changed."
        ], Response::HTTP_CREATED);
    }

    public function selectProcessor(Request $request) {
        $validated = $request->validate([
            "processor_id" => "integer|required",
        ]);

        $validated['user_id'] = auth()->user()->id;

        $userPackage = UserPackage::firstWhere("user_id", auth()->user()->id);

        if(!$userPackage) {
            UserPackage::create($validated);

            return response()->json([
                "message" => "processor successfully created."
            ], Response::HTTP_CREATED);
        }

        $userPackage->processor_id = $validated['processor_id'];
        $userPackage->save();

        return response()->json([
            "message" => "processor successfully updated."
        ], Response::HTTP_CREATED);
    }

    public function selectVga(Request $request) {
        $validated = $request->validate([
            "vga_id" => "integer|required",
        ]);

        $validated['user_id'] = auth()->user()->id;

        $userPackage = UserPackage::firstWhere("user_id", auth()->user()->id);

        if(!$userPackage) {
            UserPackage::create($validated);

            return response()->json([
                "message" => "vga successfully created."
            ], Response::HTTP_CREATED);
        }

        $userPackage->vga_id = $validated['vga_id'];
        $userPackage->save();

        return response()->json([
            "message" => "vga successfully updated."
        ], Response::HTTP_CREATED);
    }

    public function selectMotherboard(Request $request) {
        $validated = $request->validate([
            "motherboard_id" => "integer|required",
        ]);

        $validated['user_id'] = auth()->user()->id;

        $userPackage = UserPackage::firstWhere("user_id", auth()->user()->id);

        if(!$userPackage) {
            UserPackage::create($validated);

            return response()->json([
                "message" => "motherboard successfully created."
            ], Response::HTTP_CREATED);
        }

        $userPackage->motherboard_id = $validated['motherboard_id'];
        $userPackage->save();

        return response()->json([
            "message" => "motherboard successfully updated."
        ], Response::HTTP_CREATED);
    }

    public function selectPowerSupply(Request $request) {
        $validated = $request->validate([
            "power_supply_id" => "integer|required",
        ]);

        $validated['user_id'] = auth()->user()->id;

        $userPackage = UserPackage::firstWhere("user_id", auth()->user()->id);

        if(!$userPackage) {
            UserPackage::create($validated);

            return response()->json([
                "message" => "power supply successfully created."
            ], Response::HTTP_CREATED);
        }

        $userPackage->power_supply_id = $validated['power_supply_id'];
        $userPackage->save();

        return response()->json([
            "message" => "power supply successfully updated."
        ], Response::HTTP_CREATED);
    }
}
